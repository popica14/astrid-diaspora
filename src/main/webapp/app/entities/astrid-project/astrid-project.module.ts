import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectsOverviewSharedModule } from 'app/shared/shared.module';
import { AstridProjectComponent } from './astrid-project.component';
import { AstridProjectDetailComponent } from './astrid-project-detail.component';
import { AstridProjectUpdateComponent } from './astrid-project-update.component';
import { AstridProjectDeleteDialogComponent } from './astrid-project-delete-dialog.component';
import { astridProjectRoute } from './astrid-project.route';

@NgModule({
  imports: [ProjectsOverviewSharedModule, RouterModule.forChild(astridProjectRoute)],
  declarations: [AstridProjectComponent, AstridProjectDetailComponent, AstridProjectUpdateComponent, AstridProjectDeleteDialogComponent],
  entryComponents: [AstridProjectDeleteDialogComponent],
})
export class ProjectsOverviewAstridProjectModule {}
