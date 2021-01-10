import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAstridProjectSuggestion } from 'app/shared/model/astrid-project-suggestion.model';

@Component({
  selector: 'jhi-astrid-project-suggestion-detail',
  templateUrl: './astrid-project-suggestion-detail.component.html',
})
export class AstridProjectSuggestionDetailComponent implements OnInit {
  astridProjectSuggestion: IAstridProjectSuggestion | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ astridProjectSuggestion }) => (this.astridProjectSuggestion = astridProjectSuggestion));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
