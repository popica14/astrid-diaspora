import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAstridUser } from 'app/shared/model/astrid-user.model';
import { AstridUserService } from './astrid-user.service';

@Component({
  templateUrl: './astrid-user-delete-dialog.component.html',
})
export class AstridUserDeleteDialogComponent {
  astridUser?: IAstridUser;

  constructor(
    protected astridUserService: AstridUserService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.astridUserService.delete(id).subscribe(() => {
      this.eventManager.broadcast('astridUserListModification');
      this.activeModal.close();
    });
  }
}
