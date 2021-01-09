import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityCreation } from 'app/shared/model/entity-creation.model';
import { EntityCreationService } from './entity-creation.service';

@Component({
  templateUrl: './entity-creation-delete-dialog.component.html',
})
export class EntityCreationDeleteDialogComponent {
  entityCreation?: IEntityCreation;

  constructor(
    protected entityCreationService: EntityCreationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.entityCreationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('entityCreationListModification');
      this.activeModal.close();
    });
  }
}
