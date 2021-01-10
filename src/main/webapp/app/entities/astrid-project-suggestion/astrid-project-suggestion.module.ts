import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectsOverviewSharedModule } from 'app/shared/shared.module';
import { AstridProjectSuggestionComponent } from './astrid-project-suggestion.component';
import { AstridProjectSuggestionDetailComponent } from './astrid-project-suggestion-detail.component';
import { AstridProjectSuggestionUpdateComponent } from './astrid-project-suggestion-update.component';
import { AstridProjectSuggestionDeleteDialogComponent } from './astrid-project-suggestion-delete-dialog.component';
import { astridProjectSuggestionRoute } from './astrid-project-suggestion.route';

@NgModule({
  imports: [ProjectsOverviewSharedModule, RouterModule.forChild(astridProjectSuggestionRoute)],
  declarations: [
    AstridProjectSuggestionComponent,
    AstridProjectSuggestionDetailComponent,
    AstridProjectSuggestionUpdateComponent,
    AstridProjectSuggestionDeleteDialogComponent,
  ],
  entryComponents: [AstridProjectSuggestionDeleteDialogComponent],
})
export class ProjectsOverviewAstridProjectSuggestionModule {}
