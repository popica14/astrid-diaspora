import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAstridProject, AstridProject } from 'app/shared/model/astrid-project.model';
import { AstridProjectService } from './astrid-project.service';
import { AstridProjectComponent } from './astrid-project.component';
import { AstridProjectDetailComponent } from './astrid-project-detail.component';
import { AstridProjectUpdateComponent } from './astrid-project-update.component';

@Injectable({ providedIn: 'root' })
export class AstridProjectResolve implements Resolve<IAstridProject> {
  constructor(private service: AstridProjectService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAstridProject> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((astridProject: HttpResponse<AstridProject>) => {
          if (astridProject.body) {
            return of(astridProject.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AstridProject());
  }
}

export const astridProjectRoute: Routes = [
  {
    path: '',
    component: AstridProjectComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.astridProject.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AstridProjectDetailComponent,
    resolve: {
      astridProject: AstridProjectResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.astridProject.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AstridProjectUpdateComponent,
    resolve: {
      astridProject: AstridProjectResolve,
    },
    data: {
      authorities: [Authority.EDITOR, Authority.ADMIN],
      pageTitle: 'projectsOverviewApp.astridProject.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AstridProjectUpdateComponent,
    resolve: {
      astridProject: AstridProjectResolve,
    },
    data: {
      authorities: [Authority.EDITOR, Authority.ADMIN],
      pageTitle: 'projectsOverviewApp.astridProject.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
