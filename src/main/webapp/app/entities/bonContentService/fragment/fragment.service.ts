import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { IFragment } from 'app/shared/model/bonContentService/fragment.model';

type EntityResponseType = HttpResponse<IFragment>;
type EntityArrayResponseType = HttpResponse<IFragment[]>;

@Injectable({ providedIn: 'root' })
export class FragmentService {
  public resourceUrl = SERVER_API_URL + 'services/boncontentservice/api/fragments';
  public resourceSearchUrl = SERVER_API_URL + 'services/boncontentservice/api/_search/fragments';

  constructor(protected http: HttpClient) {}

  create(fragment: IFragment): Observable<EntityResponseType> {
    return this.http.post<IFragment>(this.resourceUrl, fragment, { observe: 'response' });
  }

  update(fragment: IFragment): Observable<EntityResponseType> {
    return this.http.put<IFragment>(this.resourceUrl, fragment, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFragment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFragment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFragment[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
