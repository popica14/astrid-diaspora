import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEntityCreation, EntityCreation } from 'app/shared/model/entity-creation.model';
import { EntityCreationService } from './entity-creation.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-entity-creation-update',
  templateUrl: './entity-creation-update.component.html',
})
export class EntityCreationUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    created: [null, [Validators.required]],
    createdById: [null, Validators.required],
  });

  constructor(
    protected entityCreationService: EntityCreationService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityCreation }) => {
      if (!entityCreation.id) {
        const today = moment().startOf('day');
        entityCreation.created = today;
      }

      this.updateForm(entityCreation);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(entityCreation: IEntityCreation): void {
    this.editForm.patchValue({
      id: entityCreation.id,
      created: entityCreation.created ? entityCreation.created.format(DATE_TIME_FORMAT) : null,
      createdById: entityCreation.createdById,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entityCreation = this.createFromForm();
    if (entityCreation.id !== undefined) {
      this.subscribeToSaveResponse(this.entityCreationService.update(entityCreation));
    } else {
      this.subscribeToSaveResponse(this.entityCreationService.create(entityCreation));
    }
  }

  private createFromForm(): IEntityCreation {
    return {
      ...new EntityCreation(),
      id: this.editForm.get(['id'])!.value,
      created: this.editForm.get(['created'])!.value ? moment(this.editForm.get(['created'])!.value, DATE_TIME_FORMAT) : undefined,
      createdById: this.editForm.get(['createdById'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityCreation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
