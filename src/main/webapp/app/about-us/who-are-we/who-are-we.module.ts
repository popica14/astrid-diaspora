import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectsOverviewSharedModule } from 'app/shared/shared.module';
import { WhoAreWeComponent } from './who-are-we.component';
import { WHO_ROUTE } from './who-we-are.route';

@NgModule({
  imports: [ProjectsOverviewSharedModule, RouterModule.forChild([WHO_ROUTE])],
  declarations: [WhoAreWeComponent],
})
export class WhoAreWeModule {}
