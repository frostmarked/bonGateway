import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { ILocalized } from 'app/shared/model/bonContentService/localized.model';

type EntityResponseType = HttpResponse<ILocalized>;
type EntityArrayResponseType = HttpResponse<ILocalized[]>;

@Injectable({ providedIn: 'root' })
export class LocalizedService {
  public resourceUrl = SERVER_API_URL + 'services/boncontentservice/api/localizeds';
  public resourceSearchUrl = SERVER_API_URL + 'services/boncontentservice/api/_search/localizeds';

  constructor(protected http: HttpClient) {}

  create(localized: ILocalized): Observable<EntityResponseType> {
    return this.http.post<ILocalized>(this.resourceUrl, localized, { observe: 'response' });
  }

  update(localized: ILocalized): Observable<EntityResponseType> {
    return this.http.put<ILocalized>(this.resourceUrl, localized, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILocalized>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILocalized[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILocalized[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
