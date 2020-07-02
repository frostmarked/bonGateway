import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStory } from 'app/shared/model/bonContentService/story.model';

type EntityResponseType = HttpResponse<IStory>;
type EntityArrayResponseType = HttpResponse<IStory[]>;

@Injectable({ providedIn: 'root' })
export class StoryService {
  public resourceUrl = SERVER_API_URL + 'services/boncontentservice/api/stories';

  constructor(protected http: HttpClient) {}

  create(story: IStory): Observable<EntityResponseType> {
    return this.http.post<IStory>(this.resourceUrl, story, { observe: 'response' });
  }

  update(story: IStory): Observable<EntityResponseType> {
    return this.http.put<IStory>(this.resourceUrl, story, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStory[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
