import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICattle } from 'app/shared/model/bonLivestockService/cattle.model';

type EntityResponseType = HttpResponse<ICattle>;
type EntityArrayResponseType = HttpResponse<ICattle[]>;

@Injectable({ providedIn: 'root' })
export class CattleService {
  public resourceUrl = SERVER_API_URL + 'services/bonlivestockservice/api/cattles';

  constructor(protected http: HttpClient) {}

  create(cattle: ICattle): Observable<EntityResponseType> {
    return this.http.post<ICattle>(this.resourceUrl, cattle, { observe: 'response' });
  }

  update(cattle: ICattle): Observable<EntityResponseType> {
    return this.http.put<ICattle>(this.resourceUrl, cattle, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICattle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICattle[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
