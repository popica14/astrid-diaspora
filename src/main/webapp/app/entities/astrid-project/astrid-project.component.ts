import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAstridProject } from 'app/shared/model/astrid-project.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { AstridProjectService } from './astrid-project.service';
import { AstridProjectDeleteDialogComponent } from './astrid-project-delete-dialog.component';

@Component({
  selector: 'jhi-astrid-project',
  templateUrl: './astrid-project.component.html',
})
export class AstridProjectComponent implements OnInit, OnDestroy {
  astridProjects: IAstridProject[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected astridProjectService: AstridProjectService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.astridProjects = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.astridProjectService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IAstridProject[]>) => this.paginateAstridProjects(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.astridProjects = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAstridProjects();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAstridProject): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInAstridProjects(): void {
    this.eventSubscriber = this.eventManager.subscribe('astridProjectListModification', () => this.reset());
  }

  delete(astridProject: IAstridProject): void {
    const modalRef = this.modalService.open(AstridProjectDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.astridProject = astridProject;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateAstridProjects(data: IAstridProject[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.astridProjects.push(data[i]);
      }
    }
  }
}
