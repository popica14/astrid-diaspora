import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEntityLastModification, EntityLastModification } from 'app/shared/model/entity-last-modification.model';
import { EntityLastModificationService } from './entity-last-modification.service';
import { EntityLastModificationComponent } from './entity-last-modification.component';
import { EntityLastModificationDetailComponent } from './entity-last-modification-detail.component';
import { EntityLastModificationUpdateComponent } from './entity-last-modification-update.component';

@Injectable({ providedIn: 'root' })
export class EntityLastModificationResolve implements Resolve<IEntityLastModification> {
  constructor(private service: EntityLastModificationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEntityLastModification> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((entityLastModification: HttpResponse<EntityLastModification>) => {
          if (entityLastModification.body) {
            return of(entityLastModification.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EntityLastModification());
  }
}

export const entityLastModificationRoute: Routes = [
  {
    path: '',
    component: EntityLastModificationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.entityLastModification.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EntityLastModificationDetailComponent,
    resolve: {
      entityLastModification: EntityLastModificationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.entityLastModification.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EntityLastModificationUpdateComponent,
    resolve: {
      entityLastModification: EntityLastModificationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.entityLastModification.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EntityLastModificationUpdateComponent,
    resolve: {
      entityLastModification: EntityLastModificationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.entityLastModification.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
