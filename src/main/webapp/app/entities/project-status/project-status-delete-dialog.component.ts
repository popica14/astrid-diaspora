import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProjectStatus } from 'app/shared/model/project-status.model';
import { ProjectStatusService } from './project-status.service';

@Component({
  templateUrl: './project-status-delete-dialog.component.html',
})
export class ProjectStatusDeleteDialogComponent {
  projectStatus?: IProjectStatus;

  constructor(
    protected projectStatusService: ProjectStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.projectStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('projectStatusListModification');
      this.activeModal.close();
    });
  }
}
