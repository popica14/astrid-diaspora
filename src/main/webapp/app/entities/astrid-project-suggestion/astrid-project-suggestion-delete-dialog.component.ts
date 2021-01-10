import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAstridProjectSuggestion } from 'app/shared/model/astrid-project-suggestion.model';
import { AstridProjectSuggestionService } from './astrid-project-suggestion.service';

@Component({
  templateUrl: './astrid-project-suggestion-delete-dialog.component.html',
})
export class AstridProjectSuggestionDeleteDialogComponent {
  astridProjectSuggestion?: IAstridProjectSuggestion;

  constructor(
    protected astridProjectSuggestionService: AstridProjectSuggestionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.astridProjectSuggestionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('astridProjectSuggestionListModification');
      this.activeModal.close();
    });
  }
}
