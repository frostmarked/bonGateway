import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { Maybe } from 'graphql/jsutils/Maybe';
import {
  ArticleVo,
  Context,
  CowVo,
  FindCowPicturesGQL,
  GetArticleGQL,
  GetCowGQL,
  I18n,
  PictureVo,
} from 'app/bonpublicgraphql/bonpublicgraphql';
import { map, startWith } from 'rxjs/operators';
import { DEFAULT_PICTURE, randomPictureVoFromPicsum, randomPictureVosFromPicsum } from 'app/shared/bon/picturevo-util';

@Injectable({
  providedIn: 'root',
})
export class CowService {
  constructor(private getArticleGQL: GetArticleGQL, private getCowGQL: GetCowGQL, private findCowPicturesGQL: FindCowPicturesGQL) {}

  getArticle(storyHandle: string, currentLanguage: string): Observable<Maybe<ArticleVo>> {
    const i18nkey = Object.keys(I18n).filter(key => I18n[key] === currentLanguage)[0];
    return this.getArticleGQL
      .fetch({ i18n: I18n[i18nkey], isSummary: false, isHandle: true, id: storyHandle })
      .pipe(map(result => result.data.articleVO));
  }

  getCow(earTagId: Maybe<number>, context: Maybe<Context> = undefined): Observable<Maybe<CowVo>> {
    if (!earTagId) {
      return EMPTY;
    }

    return this.getCowGQL.fetch({ earTagId, context }).pipe(map(result => result.data.cowVO));
  }

  getFirstCowPicture(earTagId: number): Observable<PictureVo> {
    return this.findCowPicturesGQL.fetch({ earTagId, size: 1 }).pipe(
      map(result => result.data.apiPublicCowsPictures),
      map(pics => (pics && pics[0] ? pics[0] : randomPictureVoFromPicsum('seed' + earTagId))),
      startWith(DEFAULT_PICTURE)
    );
  }

  getCowPictures(earTagId: number): Observable<Maybe<Array<Maybe<PictureVo>>>> {
    return this.findCowPicturesGQL.fetch({ earTagId }).pipe(
      map(result => result.data.apiPublicCowsPictures),
      map(pics => {
        if (pics && pics.length) {
          return pics;
        } else {
          return randomPictureVosFromPicsum(1, 3, 'seed' + earTagId, 1200, 900);
        }
      }),
      startWith([DEFAULT_PICTURE])
    );
  }
}
