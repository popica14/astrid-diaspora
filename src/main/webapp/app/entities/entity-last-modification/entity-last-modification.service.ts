import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEntityLastModification } from 'app/shared/model/entity-last-modification.model';

type EntityResponseType = HttpResponse<IEntityLastModification>;
type EntityArrayResponseType = HttpResponse<IEntityLastModification[]>;

@Injectable({ providedIn: 'root' })
export class EntityLastModificationService {
  public resourceUrl = SERVER_API_URL + 'api/entity-last-modifications';

  constructor(protected http: HttpClient) {}

  create(entityLastModification: IEntityLastModification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entityLastModification);
    return this.http
      .post<IEntityLastModification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(entityLastModification: IEntityLastModification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entityLastModification);
    return this.http
      .put<IEntityLastModification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEntityLastModification>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEntityLastModification[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(entityLastModification: IEntityLastModification): IEntityLastModification {
    const copy: IEntityLastModification = Object.assign({}, entityLastModification, {
      lastModified:
        entityLastModification.lastModified && entityLastModification.lastModified.isValid()
          ? entityLastModification.lastModified.toJSON()
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.lastModified = res.body.lastModified ? moment(res.body.lastModified) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((entityLastModification: IEntityLastModification) => {
        entityLastModification.lastModified = entityLastModification.lastModified ? moment(entityLastModification.lastModified) : undefined;
      });
    }
    return res;
  }
}
