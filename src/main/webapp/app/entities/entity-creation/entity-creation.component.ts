import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityCreation } from 'app/shared/model/entity-creation.model';
import { EntityCreationService } from './entity-creation.service';
import { EntityCreationDeleteDialogComponent } from './entity-creation-delete-dialog.component';

@Component({
  selector: 'jhi-entity-creation',
  templateUrl: './entity-creation.component.html',
})
export class EntityCreationComponent implements OnInit, OnDestroy {
  entityCreations?: IEntityCreation[];
  eventSubscriber?: Subscription;

  constructor(
    protected entityCreationService: EntityCreationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.entityCreationService.query().subscribe((res: HttpResponse<IEntityCreation[]>) => (this.entityCreations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEntityCreations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEntityCreation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEntityCreations(): void {
    this.eventSubscriber = this.eventManager.subscribe('entityCreationListModification', () => this.loadAll());
  }

  delete(entityCreation: IEntityCreation): void {
    const modalRef = this.modalService.open(EntityCreationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.entityCreation = entityCreation;
  }
}
