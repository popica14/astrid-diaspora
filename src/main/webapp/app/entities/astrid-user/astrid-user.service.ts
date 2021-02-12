import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
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
    const copy = this.convertDateFromClient(astridUser);
    return this.http
      .post<IAstridUser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(astridUser: IAstridUser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(astridUser);
    return this.http
      .put<IAstridUser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAstridUser>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAstridUser[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(astridUser: IAstridUser): IAstridUser {
    const copy: IAstridUser = Object.assign({}, astridUser, {
      birthDate: astridUser.birthDate && astridUser.birthDate.isValid() ? astridUser.birthDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.birthDate = res.body.birthDate ? moment(res.body.birthDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((astridUser: IAstridUser) => {
        astridUser.birthDate = astridUser.birthDate ? moment(astridUser.birthDate) : undefined;
      });
    }
    return res;
  }
}
