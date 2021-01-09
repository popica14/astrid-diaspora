import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectsOverviewSharedModule } from 'app/shared/shared.module';
import { EntityLastModificationComponent } from './entity-last-modification.component';
import { EntityLastModificationDetailComponent } from './entity-last-modification-detail.component';
import { EntityLastModificationUpdateComponent } from './entity-last-modification-update.component';
import { EntityLastModificationDeleteDialogComponent } from './entity-last-modification-delete-dialog.component';
import { entityLastModificationRoute } from './entity-last-modification.route';

@NgModule({
  imports: [ProjectsOverviewSharedModule, RouterModule.forChild(entityLastModificationRoute)],
  declarations: [
    EntityLastModificationComponent,
    EntityLastModificationDetailComponent,
    EntityLastModificationUpdateComponent,
    EntityLastModificationDeleteDialogComponent,
  ],
  entryComponents: [EntityLastModificationDeleteDialogComponent],
})
export class ProjectsOverviewEntityLastModificationModule {}
