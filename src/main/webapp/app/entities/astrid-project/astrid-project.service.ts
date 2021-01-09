import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

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
    const copy = this.convertDateFromClient(astridProject);
    return this.http
      .post<IAstridProject>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(astridProject: IAstridProject): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(astridProject);
    return this.http
      .put<IAstridProject>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAstridProject>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAstridProject[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(astridProject: IAstridProject): IAstridProject {
    const copy: IAstridProject = Object.assign({}, astridProject, {
      statusDeadline:
        astridProject.statusDeadline && astridProject.statusDeadline.isValid() ? astridProject.statusDeadline.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.statusDeadline = res.body.statusDeadline ? moment(res.body.statusDeadline) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((astridProject: IAstridProject) => {
        astridProject.statusDeadline = astridProject.statusDeadline ? moment(astridProject.statusDeadline) : undefined;
      });
    }
    return res;
  }
}
