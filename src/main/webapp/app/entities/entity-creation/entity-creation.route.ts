import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEntityCreation, EntityCreation } from 'app/shared/model/entity-creation.model';
import { EntityCreationService } from './entity-creation.service';
import { EntityCreationComponent } from './entity-creation.component';
import { EntityCreationDetailComponent } from './entity-creation-detail.component';
import { EntityCreationUpdateComponent } from './entity-creation-update.component';

@Injectable({ providedIn: 'root' })
export class EntityCreationResolve implements Resolve<IEntityCreation> {
  constructor(private service: EntityCreationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEntityCreation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((entityCreation: HttpResponse<EntityCreation>) => {
          if (entityCreation.body) {
            return of(entityCreation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EntityCreation());
  }
}

export const entityCreationRoute: Routes = [
  {
    path: '',
    component: EntityCreationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.entityCreation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EntityCreationDetailComponent,
    resolve: {
      entityCreation: EntityCreationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.entityCreation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EntityCreationUpdateComponent,
    resolve: {
      entityCreation: EntityCreationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.entityCreation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EntityCreationUpdateComponent,
    resolve: {
      entityCreation: EntityCreationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.entityCreation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
