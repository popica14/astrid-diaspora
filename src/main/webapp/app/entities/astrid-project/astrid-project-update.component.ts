import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IAstridProject, AstridProject } from 'app/shared/model/astrid-project.model';
import { AstridProjectService } from './astrid-project.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-astrid-project-update',
  templateUrl: './astrid-project-update.component.html',
})
export class AstridProjectUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    responsibleId: [],
    implementationTeams: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected astridProjectService: AstridProjectService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ astridProject }) => {
      this.updateForm(astridProject);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(astridProject: IAstridProject): void {
    this.editForm.patchValue({
      id: astridProject.id,
      name: astridProject.name,
      description: astridProject.description,
      responsibleId: astridProject.responsibleId,
      implementationTeams: astridProject.implementationTeams,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: any, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('projectsOverviewApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const astridProject = this.createFromForm();
    if (astridProject.id !== undefined) {
      this.subscribeToSaveResponse(this.astridProjectService.update(astridProject));
    } else {
      this.subscribeToSaveResponse(this.astridProjectService.create(astridProject));
    }
  }

  private createFromForm(): IAstridProject {
    return {
      ...new AstridProject(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      responsibleId: this.editForm.get(['responsibleId'])!.value,
      implementationTeams: this.editForm.get(['implementationTeams'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAstridProject>>): void {
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

  getSelected(selectedVals: IUser[], option: IUser): IUser {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
