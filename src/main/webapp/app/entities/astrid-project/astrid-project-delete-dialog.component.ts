import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAstridProject } from 'app/shared/model/astrid-project.model';
import { AstridProjectService } from './astrid-project.service';

@Component({
  templateUrl: './astrid-project-delete-dialog.component.html',
})
export class AstridProjectDeleteDialogComponent {
  astridProject?: IAstridProject;

  constructor(
    protected astridProjectService: AstridProjectService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.astridProjectService.delete(id).subscribe(() => {
      this.eventManager.broadcast('astridProjectListModification');
      this.activeModal.close();
    });
  }
}
