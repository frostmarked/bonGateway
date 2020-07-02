import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBovine } from 'app/shared/model/bonReplicaService/bovine.model';

type EntityResponseType = HttpResponse<IBovine>;
type EntityArrayResponseType = HttpResponse<IBovine[]>;

@Injectable({ providedIn: 'root' })
export class BovineService {
  public resourceUrl = SERVER_API_URL + 'services/bonreplicaservice/api/bovines';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBovine>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBovine[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(bovine: IBovine): IBovine {
    const copy: IBovine = Object.assign({}, bovine, {
      birthDate: bovine.birthDate && bovine.birthDate.isValid() ? bovine.birthDate.toJSON() : undefined,
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
      res.body.forEach((bovine: IBovine) => {
        bovine.birthDate = bovine.birthDate ? moment(bovine.birthDate) : undefined;
      });
    }
    return res;
  }
}
