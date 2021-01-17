import { Route } from '@angular/router';

import { WhoAreWeComponent } from './who-are-we.component';

export const WHO_ROUTE: Route = {
  path: '',
  component: WhoAreWeComponent,
  data: {
    authorities: [],
    pageTitle: 'globalExtra.pages.whoAreWe.title',
  },
};
