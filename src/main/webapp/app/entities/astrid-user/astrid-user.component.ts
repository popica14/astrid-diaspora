import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAstridUser } from 'app/shared/model/astrid-user.model';
import { AstridUserService } from './astrid-user.service';
import { AstridUserDeleteDialogComponent } from './astrid-user-delete-dialog.component';

@Component({
  selector: 'jhi-astrid-user',
  templateUrl: './astrid-user.component.html',
})
export class AstridUserComponent implements OnInit, OnDestroy {
  astridUsers?: IAstridUser[];
  eventSubscriber?: Subscription;

  constructor(protected astridUserService: AstridUserService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.astridUserService.query().subscribe((res: HttpResponse<IAstridUser[]>) => (this.astridUsers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAstridUsers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAstridUser): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAstridUsers(): void {
    this.eventSubscriber = this.eventManager.subscribe('astridUserListModification', () => this.loadAll());
  }

  delete(astridUser: IAstridUser): void {
    const modalRef = this.modalService.open(AstridUserDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.astridUser = astridUser;
  }
}
