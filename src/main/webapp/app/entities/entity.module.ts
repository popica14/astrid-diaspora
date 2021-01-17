import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Authority } from 'app/shared/constants/authority.constants';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'astrid-project',
        loadChildren: () => import('./astrid-project/astrid-project.module').then(m => m.ProjectsOverviewAstridProjectModule),
      },
      {
        path: 'beneficiary',
        data: {
          authorities: [Authority.ADMIN],
        },
        loadChildren: () => import('./beneficiary/beneficiary.module').then(m => m.ProjectsOverviewBeneficiaryModule),
      },
      {
        path: 'location',
        data: {
          authorities: [Authority.ADMIN],
        },
        loadChildren: () => import('./location/location.module').then(m => m.ProjectsOverviewLocationModule),
      },
      {
        path: 'project-status',
        data: {
          authorities: [Authority.ADMIN],
        },
        loadChildren: () => import('./project-status/project-status.module').then(m => m.ProjectsOverviewProjectStatusModule),
      },
      {
        path: 'entity-creation',
        data: {
          authorities: [Authority.ADMIN],
        },
        loadChildren: () => import('./entity-creation/entity-creation.module').then(m => m.ProjectsOverviewEntityCreationModule),
      },
      {
        path: 'entity-last-modification',
        data: {
          authorities: [Authority.ADMIN],
        },
        loadChildren: () =>
          import('./entity-last-modification/entity-last-modification.module').then(m => m.ProjectsOverviewEntityLastModificationModule),
      },
      {
        path: 'astrid-project-suggestion',
        loadChildren: () =>
          import('./astrid-project-suggestion/astrid-project-suggestion.module').then(m => m.ProjectsOverviewAstridProjectSuggestionModule),
      },
      {
        path: 'astrid-user',
        loadChildren: () => import('./astrid-user/astrid-user.module').then(m => m.ProjectsOverviewAstridUserModule),
      },
      {
        path: 'currency',
        data: {
          authorities: [Authority.ADMIN],
        },
        loadChildren: () => import('./currency/currency.module').then(m => m.ProjectsOverviewCurrencyModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ProjectsOverviewEntityModule {}
