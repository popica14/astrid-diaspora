import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

@NgModule({
  imports: [
    /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
    RouterModule.forChild([
      {
        path: 'who-are-we',
        loadChildren: () => import('./who-are-we/who-are-we.module').then(m => m.WhoAreWeModule),
        data: {
          pageTitle: 'globalExtra.pages.whoAreWe.title',
        },
      },
      {
        path: 'contact',
        loadChildren: () => import('./contact/contact.module').then(m => m.ContactModule),
        data: {
          pageTitle: 'globalExtra.pages.contact.title',
        },
      },
      /* jhipster-needle-add-admin-route - JHipster will add admin routes here */
    ]),
  ],
})
export class AboutUsRoutingModule {}
