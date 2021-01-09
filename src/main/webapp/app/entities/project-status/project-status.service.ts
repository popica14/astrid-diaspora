import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProjectStatus } from 'app/shared/model/project-status.model';

type EntityResponseType = HttpResponse<IProjectStatus>;
type EntityArrayResponseType = HttpResponse<IProjectStatus[]>;

@Injectable({ providedIn: 'root' })
export class ProjectStatusService {
  public resourceUrl = SERVER_API_URL + 'api/project-statuses';

  constructor(protected http: HttpClient) {}

  create(projectStatus: IProjectStatus): Observable<EntityResponseType> {
    return this.http.post<IProjectStatus>(this.resourceUrl, projectStatus, { observe: 'response' });
  }

  update(projectStatus: IProjectStatus): Observable<EntityResponseType> {
    return this.http.put<IProjectStatus>(this.resourceUrl, projectStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProjectStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProjectStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
