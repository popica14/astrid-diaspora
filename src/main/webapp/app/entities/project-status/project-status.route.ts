import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProjectStatus, ProjectStatus } from 'app/shared/model/project-status.model';
import { ProjectStatusService } from './project-status.service';
import { ProjectStatusComponent } from './project-status.component';
import { ProjectStatusDetailComponent } from './project-status-detail.component';
import { ProjectStatusUpdateComponent } from './project-status-update.component';

@Injectable({ providedIn: 'root' })
export class ProjectStatusResolve implements Resolve<IProjectStatus> {
  constructor(private service: ProjectStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProjectStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((projectStatus: HttpResponse<ProjectStatus>) => {
          if (projectStatus.body) {
            return of(projectStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProjectStatus());
  }
}

export const projectStatusRoute: Routes = [
  {
    path: '',
    component: ProjectStatusComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.projectStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectStatusDetailComponent,
    resolve: {
      projectStatus: ProjectStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.projectStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectStatusUpdateComponent,
    resolve: {
      projectStatus: ProjectStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.projectStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectStatusUpdateComponent,
    resolve: {
      projectStatus: ProjectStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.projectStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
