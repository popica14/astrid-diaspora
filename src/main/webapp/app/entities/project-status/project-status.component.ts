import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProjectStatus } from 'app/shared/model/project-status.model';
import { ProjectStatusService } from './project-status.service';
import { ProjectStatusDeleteDialogComponent } from './project-status-delete-dialog.component';

@Component({
  selector: 'jhi-project-status',
  templateUrl: './project-status.component.html',
})
export class ProjectStatusComponent implements OnInit, OnDestroy {
  projectStatuses?: IProjectStatus[];
  eventSubscriber?: Subscription;

  constructor(
    protected projectStatusService: ProjectStatusService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.projectStatusService.query().subscribe((res: HttpResponse<IProjectStatus[]>) => (this.projectStatuses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProjectStatuses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProjectStatus): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProjectStatuses(): void {
    this.eventSubscriber = this.eventManager.subscribe('projectStatusListModification', () => this.loadAll());
  }

  delete(projectStatus: IProjectStatus): void {
    const modalRef = this.modalService.open(ProjectStatusDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.projectStatus = projectStatus;
  }
}
