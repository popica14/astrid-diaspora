import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEntityLastModification, EntityLastModification } from 'app/shared/model/entity-last-modification.model';
import { EntityLastModificationService } from './entity-last-modification.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-entity-last-modification-update',
  templateUrl: './entity-last-modification-update.component.html',
})
export class EntityLastModificationUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    lastModified: [],
    lastModifiedById: [],
  });

  constructor(
    protected entityLastModificationService: EntityLastModificationService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityLastModification }) => {
      if (!entityLastModification.id) {
        const today = moment().startOf('day');
        entityLastModification.lastModified = today;
      }

      this.updateForm(entityLastModification);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(entityLastModification: IEntityLastModification): void {
    this.editForm.patchValue({
      id: entityLastModification.id,
      lastModified: entityLastModification.lastModified ? entityLastModification.lastModified.format(DATE_TIME_FORMAT) : null,
      lastModifiedById: entityLastModification.lastModifiedById,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entityLastModification = this.createFromForm();
    if (entityLastModification.id !== undefined) {
      this.subscribeToSaveResponse(this.entityLastModificationService.update(entityLastModification));
    } else {
      this.subscribeToSaveResponse(this.entityLastModificationService.create(entityLastModification));
    }
  }

  private createFromForm(): IEntityLastModification {
    return {
      ...new EntityLastModification(),
      id: this.editForm.get(['id'])!.value,
      lastModified: this.editForm.get(['lastModified'])!.value
        ? moment(this.editForm.get(['lastModified'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedById: this.editForm.get(['lastModifiedById'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityLastModification>>): void {
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
