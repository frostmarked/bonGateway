import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISourceFile } from 'app/shared/model/bonReplicaService/source-file.model';

type EntityResponseType = HttpResponse<ISourceFile>;
type EntityArrayResponseType = HttpResponse<ISourceFile[]>;

@Injectable({ providedIn: 'root' })
export class SourceFileService {
  public resourceUrl = SERVER_API_URL + 'services/bonreplicaservice/api/source-files';

  constructor(protected http: HttpClient) {}

  create(sourceFile: ISourceFile): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sourceFile);
    return this.http
      .post<ISourceFile>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(sourceFile: ISourceFile): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sourceFile);
    return this.http
      .put<ISourceFile>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISourceFile>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISourceFile[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  process(id: number): Observable<HttpResponse<{}>> {
    return this.http.post(`${this.resourceUrl}/${id}/process`, {}, { observe: 'response' });
  }

  protected convertDateFromClient(sourceFile: ISourceFile): ISourceFile {
    const copy: ISourceFile = Object.assign({}, sourceFile, {
      processed: sourceFile.processed && sourceFile.processed.isValid() ? sourceFile.processed.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.processed = res.body.processed ? moment(res.body.processed) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((sourceFile: ISourceFile) => {
        sourceFile.processed = sourceFile.processed ? moment(sourceFile.processed) : undefined;
      });
    }
    return res;
  }
}
