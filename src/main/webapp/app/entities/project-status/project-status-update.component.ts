import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProjectStatus, ProjectStatus } from 'app/shared/model/project-status.model';
import { ProjectStatusService } from './project-status.service';

@Component({
  selector: 'jhi-project-status-update',
  templateUrl: './project-status-update.component.html',
})
export class ProjectStatusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    daysToNotification: [null, [Validators.required]],
  });

  constructor(protected projectStatusService: ProjectStatusService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ projectStatus }) => {
      this.updateForm(projectStatus);
    });
  }

  updateForm(projectStatus: IProjectStatus): void {
    this.editForm.patchValue({
      id: projectStatus.id,
      name: projectStatus.name,
      daysToNotification: projectStatus.daysToNotification,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const projectStatus = this.createFromForm();
    if (projectStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.projectStatusService.update(projectStatus));
    } else {
      this.subscribeToSaveResponse(this.projectStatusService.create(projectStatus));
    }
  }

  private createFromForm(): IProjectStatus {
    return {
      ...new ProjectStatus(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      daysToNotification: this.editForm.get(['daysToNotification'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProjectStatus>>): void {
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
}
