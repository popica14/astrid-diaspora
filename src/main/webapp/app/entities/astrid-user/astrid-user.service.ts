import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAstridUser } from 'app/shared/model/astrid-user.model';

type EntityResponseType = HttpResponse<IAstridUser>;
type EntityArrayResponseType = HttpResponse<IAstridUser[]>;

@Injectable({ providedIn: 'root' })
export class AstridUserService {
  public resourceUrl = SERVER_API_URL + 'api/astrid-users';

  constructor(protected http: HttpClient) {}

  create(astridUser: IAstridUser): Observable<EntityResponseType> {
    return this.http.post<IAstridUser>(this.resourceUrl, astridUser, { observe: 'response' });
  }

  update(astridUser: IAstridUser): Observable<EntityResponseType> {
    return this.http.put<IAstridUser>(this.resourceUrl, astridUser, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAstridUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAstridUser[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
