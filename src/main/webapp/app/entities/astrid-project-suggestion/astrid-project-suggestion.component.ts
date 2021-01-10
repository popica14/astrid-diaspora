import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAstridProjectSuggestion } from 'app/shared/model/astrid-project-suggestion.model';
import { AstridProjectSuggestionService } from './astrid-project-suggestion.service';
import { AstridProjectSuggestionDeleteDialogComponent } from './astrid-project-suggestion-delete-dialog.component';

@Component({
  selector: 'jhi-astrid-project-suggestion',
  templateUrl: './astrid-project-suggestion.component.html',
})
export class AstridProjectSuggestionComponent implements OnInit, OnDestroy {
  astridProjectSuggestions?: IAstridProjectSuggestion[];
  eventSubscriber?: Subscription;

  constructor(
    protected astridProjectSuggestionService: AstridProjectSuggestionService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.astridProjectSuggestionService
      .query()
      .subscribe((res: HttpResponse<IAstridProjectSuggestion[]>) => (this.astridProjectSuggestions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAstridProjectSuggestions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAstridProjectSuggestion): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInAstridProjectSuggestions(): void {
    this.eventSubscriber = this.eventManager.subscribe('astridProjectSuggestionListModification', () => this.loadAll());
  }

  delete(astridProjectSuggestion: IAstridProjectSuggestion): void {
    const modalRef = this.modalService.open(AstridProjectSuggestionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.astridProjectSuggestion = astridProjectSuggestion;
  }
}
