import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'astrid-project',
        loadChildren: () => import('./astrid-project/astrid-project.module').then(m => m.ProjectsOverviewAstridProjectModule),
      },
      {
        path: 'beneficiary',
        loadChildren: () => import('./beneficiary/beneficiary.module').then(m => m.ProjectsOverviewBeneficiaryModule),
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.ProjectsOverviewLocationModule),
      },
      {
        path: 'project-status',
        loadChildren: () => import('./project-status/project-status.module').then(m => m.ProjectsOverviewProjectStatusModule),
      },
      {
        path: 'entity-creation',
        loadChildren: () => import('./entity-creation/entity-creation.module').then(m => m.ProjectsOverviewEntityCreationModule),
      },
      {
        path: 'entity-last-modification',
        loadChildren: () =>
          import('./entity-last-modification/entity-last-modification.module').then(m => m.ProjectsOverviewEntityLastModificationModule),
      },
      {
        path: 'astrid-project-suggestion',
        loadChildren: () =>
          import('./astrid-project-suggestion/astrid-project-suggestion.module').then(m => m.ProjectsOverviewAstridProjectSuggestionModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ProjectsOverviewEntityModule {}
