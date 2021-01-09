import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityLastModification } from 'app/shared/model/entity-last-modification.model';
import { EntityLastModificationService } from './entity-last-modification.service';

@Component({
  templateUrl: './entity-last-modification-delete-dialog.component.html',
})
export class EntityLastModificationDeleteDialogComponent {
  entityLastModification?: IEntityLastModification;

  constructor(
    protected entityLastModificationService: EntityLastModificationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.entityLastModificationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('entityLastModificationListModification');
      this.activeModal.close();
    });
  }
}
