import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectsOverviewSharedModule } from 'app/shared/shared.module';
import { ProjectStatusComponent } from './project-status.component';
import { ProjectStatusDetailComponent } from './project-status-detail.component';
import { ProjectStatusUpdateComponent } from './project-status-update.component';
import { ProjectStatusDeleteDialogComponent } from './project-status-delete-dialog.component';
import { projectStatusRoute } from './project-status.route';

@NgModule({
  imports: [ProjectsOverviewSharedModule, RouterModule.forChild(projectStatusRoute)],
  declarations: [ProjectStatusComponent, ProjectStatusDetailComponent, ProjectStatusUpdateComponent, ProjectStatusDeleteDialogComponent],
  entryComponents: [ProjectStatusDeleteDialogComponent],
})
export class ProjectsOverviewProjectStatusModule {}
