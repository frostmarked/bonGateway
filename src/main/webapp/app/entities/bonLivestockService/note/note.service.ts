import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INote } from 'app/shared/model/bonLivestockService/note.model';

type EntityResponseType = HttpResponse<INote>;
type EntityArrayResponseType = HttpResponse<INote[]>;

@Injectable({ providedIn: 'root' })
export class NoteService {
  public resourceUrl = SERVER_API_URL + 'services/bonlivestockservice/api/notes';

  constructor(protected http: HttpClient) {}

  create(note: INote): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(note);
    return this.http
      .post<INote>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(note: INote): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(note);
    return this.http
      .put<INote>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INote>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INote[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(note: INote): INote {
    const copy: INote = Object.assign({}, note, {
      actualDate: note.actualDate && note.actualDate.isValid() ? note.actualDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.actualDate = res.body.actualDate ? moment(res.body.actualDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((note: INote) => {
        note.actualDate = note.actualDate ? moment(note.actualDate) : undefined;
      });
    }
    return res;
  }
}
