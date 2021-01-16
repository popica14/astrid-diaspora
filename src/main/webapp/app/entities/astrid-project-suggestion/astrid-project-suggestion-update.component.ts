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
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';

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
    goal: [null, [Validators.required]],
    documentation1: [],
    documentation1ContentType: [],
    documentation2: [],
    documentation2ContentType: [],
    documentation3: [],
    documentation3ContentType: [],
    documentation4: [],
    documentation4ContentType: [],
    documentation5: [],
    documentation5ContentType: [],
    initiatorId: [null, Validators.required],
    statusId: [],
  });
  currentAccount: Account | undefined;

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected astridProjectSuggestionService: AstridProjectSuggestionService,
    protected userService: UserService,
    protected projectStatusService: ProjectStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    protected accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ astridProjectSuggestion }) => {
      this.updateForm(astridProjectSuggestion);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.projectStatusService.query().subscribe((res: HttpResponse<IProjectStatus[]>) => (this.projectstatuses = res.body || []));

      this.accountService.identity().subscribe(account => {
        if (account) {
          this.currentAccount = account;
        }
      });
    });
  }

  updateForm(astridProjectSuggestion: IAstridProjectSuggestion): void {
    this.editForm.patchValue({
      id: astridProjectSuggestion.id,
      name: astridProjectSuggestion.name,
      shortDescription: astridProjectSuggestion.shortDescription,
      goal: astridProjectSuggestion.goal,
      documentation1: astridProjectSuggestion.documentation1,
      documentation1ContentType: astridProjectSuggestion.documentation1ContentType,
      documentation2: astridProjectSuggestion.documentation2,
      documentation2ContentType: astridProjectSuggestion.documentation2ContentType,
      documentation3: astridProjectSuggestion.documentation3,
      documentation3ContentType: astridProjectSuggestion.documentation3ContentType,
      documentation4: astridProjectSuggestion.documentation4,
      documentation4ContentType: astridProjectSuggestion.documentation4ContentType,
      documentation5: astridProjectSuggestion.documentation5,
      documentation5ContentType: astridProjectSuggestion.documentation5ContentType,
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
      goal: this.editForm.get(['goal'])!.value,
      documentation1ContentType: this.editForm.get(['documentation1ContentType'])!.value,
      documentation1: this.editForm.get(['documentation1'])!.value,
      documentation2ContentType: this.editForm.get(['documentation2ContentType'])!.value,
      documentation2: this.editForm.get(['documentation2'])!.value,
      documentation3ContentType: this.editForm.get(['documentation3ContentType'])!.value,
      documentation3: this.editForm.get(['documentation3'])!.value,
      documentation4ContentType: this.editForm.get(['documentation4ContentType'])!.value,
      documentation4: this.editForm.get(['documentation4'])!.value,
      documentation5ContentType: this.editForm.get(['documentation5ContentType'])!.value,
      documentation5: this.editForm.get(['documentation5'])!.value,
      initiatorId: this.users.find(e => e.login === this.currentAccount?.login)?.id,
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
