import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEntityCreation } from 'app/shared/model/entity-creation.model';

type EntityResponseType = HttpResponse<IEntityCreation>;
type EntityArrayResponseType = HttpResponse<IEntityCreation[]>;

@Injectable({ providedIn: 'root' })
export class EntityCreationService {
  public resourceUrl = SERVER_API_URL + 'api/entity-creations';

  constructor(protected http: HttpClient) {}

  create(entityCreation: IEntityCreation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entityCreation);
    return this.http
      .post<IEntityCreation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(entityCreation: IEntityCreation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(entityCreation);
    return this.http
      .put<IEntityCreation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEntityCreation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEntityCreation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(entityCreation: IEntityCreation): IEntityCreation {
    const copy: IEntityCreation = Object.assign({}, entityCreation, {
      created: entityCreation.created && entityCreation.created.isValid() ? entityCreation.created.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.created = res.body.created ? moment(res.body.created) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((entityCreation: IEntityCreation) => {
        entityCreation.created = entityCreation.created ? moment(entityCreation.created) : undefined;
      });
    }
    return res;
  }
}
