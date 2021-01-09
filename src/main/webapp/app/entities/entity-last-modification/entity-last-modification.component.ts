import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEntityLastModification } from 'app/shared/model/entity-last-modification.model';
import { EntityLastModificationService } from './entity-last-modification.service';
import { EntityLastModificationDeleteDialogComponent } from './entity-last-modification-delete-dialog.component';

@Component({
  selector: 'jhi-entity-last-modification',
  templateUrl: './entity-last-modification.component.html',
})
export class EntityLastModificationComponent implements OnInit, OnDestroy {
  entityLastModifications?: IEntityLastModification[];
  eventSubscriber?: Subscription;

  constructor(
    protected entityLastModificationService: EntityLastModificationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.entityLastModificationService
      .query()
      .subscribe((res: HttpResponse<IEntityLastModification[]>) => (this.entityLastModifications = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEntityLastModifications();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEntityLastModification): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEntityLastModifications(): void {
    this.eventSubscriber = this.eventManager.subscribe('entityLastModificationListModification', () => this.loadAll());
  }

  delete(entityLastModification: IEntityLastModification): void {
    const modalRef = this.modalService.open(EntityLastModificationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.entityLastModification = entityLastModification;
  }
}
