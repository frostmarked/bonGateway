import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IJournalEntry } from 'app/shared/model/bonReplicaService/journal-entry.model';

type EntityResponseType = HttpResponse<IJournalEntry>;
type EntityArrayResponseType = HttpResponse<IJournalEntry[]>;

@Injectable({ providedIn: 'root' })
export class JournalEntryService {
  public resourceUrl = SERVER_API_URL + 'services/bonreplicaservice/api/journal-entries';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IJournalEntry>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IJournalEntry[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(journalEntry: IJournalEntry): IJournalEntry {
    const copy: IJournalEntry = Object.assign({}, journalEntry, {
      date: journalEntry.date && journalEntry.date.isValid() ? journalEntry.date.toJSON() : undefined,
      edited: journalEntry.edited && journalEntry.edited.isValid() ? journalEntry.edited.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? moment(res.body.date) : undefined;
      res.body.edited = res.body.edited ? moment(res.body.edited) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((journalEntry: IJournalEntry) => {
        journalEntry.date = journalEntry.date ? moment(journalEntry.date) : undefined;
        journalEntry.edited = journalEntry.edited ? moment(journalEntry.edited) : undefined;
      });
    }
    return res;
  }
}
