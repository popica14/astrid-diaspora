import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAstridProject } from 'app/shared/model/astrid-project.model';

type EntityResponseType = HttpResponse<IAstridProject>;
type EntityArrayResponseType = HttpResponse<IAstridProject[]>;

@Injectable({ providedIn: 'root' })
export class AstridProjectService {
  public resourceUrl = SERVER_API_URL + 'api/astrid-projects';

  constructor(protected http: HttpClient) {}

  create(astridProject: IAstridProject): Observable<EntityResponseType> {
    return this.http.post<IAstridProject>(this.resourceUrl, astridProject, { observe: 'response' });
  }

  update(astridProject: IAstridProject): Observable<EntityResponseType> {
    return this.http.put<IAstridProject>(this.resourceUrl, astridProject, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAstridProject>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAstridProject[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
