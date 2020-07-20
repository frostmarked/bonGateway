import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBlup } from 'app/shared/model/bonReplicaService/blup.model';

type EntityResponseType = HttpResponse<IBlup>;
type EntityArrayResponseType = HttpResponse<IBlup[]>;

@Injectable({ providedIn: 'root' })
export class BlupService {
  public resourceUrl = SERVER_API_URL + 'services/bonreplicaservice/api/blups';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBlup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBlup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
}
