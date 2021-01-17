import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectsOverviewSharedModule } from 'app/shared/shared.module';
import { ContactComponent } from './contact.component';
import { CONTACT_ROUTE } from './contact.route';

@NgModule({
  imports: [ProjectsOverviewSharedModule, RouterModule.forChild([CONTACT_ROUTE])],
  declarations: [ContactComponent],
})
export class ContactModule {}
