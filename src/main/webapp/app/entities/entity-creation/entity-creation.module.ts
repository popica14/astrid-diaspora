import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectsOverviewSharedModule } from 'app/shared/shared.module';
import { EntityCreationComponent } from './entity-creation.component';
import { EntityCreationDetailComponent } from './entity-creation-detail.component';
import { EntityCreationUpdateComponent } from './entity-creation-update.component';
import { EntityCreationDeleteDialogComponent } from './entity-creation-delete-dialog.component';
import { entityCreationRoute } from './entity-creation.route';

@NgModule({
  imports: [ProjectsOverviewSharedModule, RouterModule.forChild(entityCreationRoute)],
  declarations: [
    EntityCreationComponent,
    EntityCreationDetailComponent,
    EntityCreationUpdateComponent,
    EntityCreationDeleteDialogComponent,
  ],
  entryComponents: [EntityCreationDeleteDialogComponent],
})
export class ProjectsOverviewEntityCreationModule {}
