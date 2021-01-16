import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectsOverviewSharedModule } from 'app/shared/shared.module';
import { AstridUserComponent } from './astrid-user.component';
import { AstridUserDetailComponent } from './astrid-user-detail.component';
import { AstridUserUpdateComponent } from './astrid-user-update.component';
import { AstridUserDeleteDialogComponent } from './astrid-user-delete-dialog.component';
import { astridUserRoute } from './astrid-user.route';

@NgModule({
  imports: [ProjectsOverviewSharedModule, RouterModule.forChild(astridUserRoute)],
  declarations: [AstridUserComponent, AstridUserDetailComponent, AstridUserUpdateComponent, AstridUserDeleteDialogComponent],
  entryComponents: [AstridUserDeleteDialogComponent],
})
export class ProjectsOverviewAstridUserModule {}
