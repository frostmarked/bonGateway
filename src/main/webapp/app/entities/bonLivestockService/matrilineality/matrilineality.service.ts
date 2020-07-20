import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMatrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';

type EntityResponseType = HttpResponse<IMatrilineality>;
type EntityArrayResponseType = HttpResponse<IMatrilineality[]>;

@Injectable({ providedIn: 'root' })
export class MatrilinealityService {
  public resourceUrl = SERVER_API_URL + 'services/bonlivestockservice/api/matrilinealities';

  constructor(protected http: HttpClient) {}

  create(matrilineality: IMatrilineality): Observable<EntityResponseType> {
    return this.http.post<IMatrilineality>(this.resourceUrl, matrilineality, { observe: 'response' });
  }

  update(matrilineality: IMatrilineality): Observable<EntityResponseType> {
    return this.http.put<IMatrilineality>(this.resourceUrl, matrilineality, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMatrilineality>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMatrilineality[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
