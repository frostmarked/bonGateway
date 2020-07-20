import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPasture } from 'app/shared/model/bonLivestockService/pasture.model';

type EntityResponseType = HttpResponse<IPasture>;
type EntityArrayResponseType = HttpResponse<IPasture[]>;

@Injectable({ providedIn: 'root' })
export class PastureService {
  public resourceUrl = SERVER_API_URL + 'services/bonlivestockservice/api/pastures';

  constructor(protected http: HttpClient) {}

  create(pasture: IPasture): Observable<EntityResponseType> {
    return this.http.post<IPasture>(this.resourceUrl, pasture, { observe: 'response' });
  }

  update(pasture: IPasture): Observable<EntityResponseType> {
    return this.http.put<IPasture>(this.resourceUrl, pasture, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPasture>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPasture[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
