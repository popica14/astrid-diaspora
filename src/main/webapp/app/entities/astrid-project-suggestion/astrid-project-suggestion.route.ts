import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAstridProjectSuggestion, AstridProjectSuggestion } from 'app/shared/model/astrid-project-suggestion.model';
import { AstridProjectSuggestionService } from './astrid-project-suggestion.service';
import { AstridProjectSuggestionComponent } from './astrid-project-suggestion.component';
import { AstridProjectSuggestionDetailComponent } from './astrid-project-suggestion-detail.component';
import { AstridProjectSuggestionUpdateComponent } from './astrid-project-suggestion-update.component';

@Injectable({ providedIn: 'root' })
export class AstridProjectSuggestionResolve implements Resolve<IAstridProjectSuggestion> {
  constructor(private service: AstridProjectSuggestionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAstridProjectSuggestion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((astridProjectSuggestion: HttpResponse<AstridProjectSuggestion>) => {
          if (astridProjectSuggestion.body) {
            return of(astridProjectSuggestion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AstridProjectSuggestion());
  }
}

export const astridProjectSuggestionRoute: Routes = [
  {
    path: '',
    component: AstridProjectSuggestionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.astridProjectSuggestion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AstridProjectSuggestionDetailComponent,
    resolve: {
      astridProjectSuggestion: AstridProjectSuggestionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.astridProjectSuggestion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AstridProjectSuggestionUpdateComponent,
    resolve: {
      astridProjectSuggestion: AstridProjectSuggestionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.astridProjectSuggestion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AstridProjectSuggestionUpdateComponent,
    resolve: {
      astridProjectSuggestion: AstridProjectSuggestionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'projectsOverviewApp.astridProjectSuggestion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
