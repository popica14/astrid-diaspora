import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAstridProjectSuggestion } from 'app/shared/model/astrid-project-suggestion.model';

type EntityResponseType = HttpResponse<IAstridProjectSuggestion>;
type EntityArrayResponseType = HttpResponse<IAstridProjectSuggestion[]>;

@Injectable({ providedIn: 'root' })
export class AstridProjectSuggestionService {
  public resourceUrl = SERVER_API_URL + 'api/astrid-project-suggestions';

  constructor(protected http: HttpClient) {}

  create(astridProjectSuggestion: IAstridProjectSuggestion): Observable<EntityResponseType> {
    return this.http.post<IAstridProjectSuggestion>(this.resourceUrl, astridProjectSuggestion, { observe: 'response' });
  }

  update(astridProjectSuggestion: IAstridProjectSuggestion): Observable<EntityResponseType> {
    return this.http.put<IAstridProjectSuggestion>(this.resourceUrl, astridProjectSuggestion, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAstridProjectSuggestion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAstridProjectSuggestion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
