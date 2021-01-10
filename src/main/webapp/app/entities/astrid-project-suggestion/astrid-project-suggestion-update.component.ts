import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IAstridProjectSuggestion, AstridProjectSuggestion } from 'app/shared/model/astrid-project-suggestion.model';
import { AstridProjectSuggestionService } from './astrid-project-suggestion.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IProjectStatus } from 'app/shared/model/project-status.model';
import { ProjectStatusService } from 'app/entities/project-status/project-status.service';

type SelectableEntity = IUser | IProjectStatus;

@Component({
  selector: 'jhi-astrid-project-suggestion-update',
  templateUrl: './astrid-project-suggestion-update.component.html',
})
export class AstridProjectSuggestionUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  projectstatuses: IProjectStatus[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    shortDescription: [null, [Validators.required]],
    documentation: [],
    documentationContentType: [],
    goal: [],
    initiatorId: [null, Validators.required],
    statusId: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected astridProjectSuggestionService: AstridProjectSuggestionService,
    protected userService: UserService,
    protected projectStatusService: ProjectStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ astridProjectSuggestion }) => {
      this.updateForm(astridProjectSuggestion);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.projectStatusService.query().subscribe((res: HttpResponse<IProjectStatus[]>) => (this.projectstatuses = res.body || []));
    });
  }

  updateForm(astridProjectSuggestion: IAstridProjectSuggestion): void {
    this.editForm.patchValue({
      id: astridProjectSuggestion.id,
      name: astridProjectSuggestion.name,
      shortDescription: astridProjectSuggestion.shortDescription,
      documentation: astridProjectSuggestion.documentation,
      documentationContentType: astridProjectSuggestion.documentationContentType,
      goal: astridProjectSuggestion.goal,
      initiatorId: astridProjectSuggestion.initiatorId,
      statusId: astridProjectSuggestion.statusId,
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
    const astridProjectSuggestion = this.createFromForm();
    if (astridProjectSuggestion.id !== undefined) {
      this.subscribeToSaveResponse(this.astridProjectSuggestionService.update(astridProjectSuggestion));
    } else {
      this.subscribeToSaveResponse(this.astridProjectSuggestionService.create(astridProjectSuggestion));
    }
  }

  private createFromForm(): IAstridProjectSuggestion {
    return {
      ...new AstridProjectSuggestion(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      shortDescription: this.editForm.get(['shortDescription'])!.value,
      documentationContentType: this.editForm.get(['documentationContentType'])!.value,
      documentation: this.editForm.get(['documentation'])!.value,
      goal: this.editForm.get(['goal'])!.value,
      initiatorId: this.editForm.get(['initiatorId'])!.value,
      statusId: this.editForm.get(['statusId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAstridProjectSuggestion>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
