import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAstridUser, AstridUser } from 'app/shared/model/astrid-user.model';
import { AstridUserService } from './astrid-user.service';
import { AstridUserComponent } from './astrid-user.component';
import { AstridUserDetailComponent } from './astrid-user-detail.component';
import { AstridUserUpdateComponent } from './astrid-user-update.component';

@Injectable({ providedIn: 'root' })
export class AstridUserResolve implements Resolve<IAstridUser> {
  constructor(private service: AstridUserService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAstridUser> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((astridUser: HttpResponse<AstridUser>) => {
          if (astridUser.body) {
            return of(astridUser.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AstridUser());
  }
}

export const astridUserRoute: Routes = [
  {
    path: '',
    component: AstridUserComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.astridUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AstridUserDetailComponent,
    resolve: {
      astridUser: AstridUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.astridUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AstridUserUpdateComponent,
    resolve: {
      astridUser: AstridUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.astridUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AstridUserUpdateComponent,
    resolve: {
      astridUser: AstridUserResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.astridUser.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
