import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'astrid-project',
        loadChildren: () => import('./astrid-project/astrid-project.module').then(m => m.ProjectsOverviewAstridProjectModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ProjectsOverviewEntityModule {}
