import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {
  FindCowPhotosGQL,
  GetArticleGQL,
  ArticleVo,
  I18n,
  CowVo,
  PhotographVo,
  Visibility,
  GetCowGQL,
} from 'app/bonpublicgraphql/bonpublicgraphql';
import { map, finalize, startWith } from 'rxjs/operators';
import { Observable, BehaviorSubject, EMPTY } from 'rxjs';
import { JhiLanguageService } from 'ng-jhipster';
import { Maybe } from 'graphql/jsutils/Maybe';

const DEFAULT_PHOTOGRAPH = {
  image: 'R0lGODlhAQABAIAAAMLCwgAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==',
  imageContentType: 'image/gif',
  caption: 'image is missing',
  visibility: Visibility.RoleAnonymous,
  taken: new Date().toISOString(),
} as PhotographVo;

@Component({
  selector: 'jhi-dam-details',
  templateUrl: './dam-details.component.html',
  styleUrls: ['./dam-details.component.scss'],
})
export class DamDetailsComponent implements OnInit {
  loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  dam?: CowVo;
  article$?: Observable<Maybe<ArticleVo>>;
  photos$?: Observable<Maybe<Array<Maybe<PhotographVo>>>>;
  matri$?: Observable<Maybe<CowVo>>;
  patri$?: Observable<Maybe<CowVo>>;

  constructor(
    public activatedRoute: ActivatedRoute,
    private languageService: JhiLanguageService,
    private getArticleGQL: GetArticleGQL,
    private getCowGQL: GetCowGQL,
    private findCowPhotosGQL: FindCowPhotosGQL
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.dam = data.cowVo;
      this.article$ = this.dam!.storyHandle ? this.getArticle(this.dam!.storyHandle, this.languageService.getCurrentLanguage()) : EMPTY;
      this.photos$ = this.getCowPhotos(this.dam!.earTagId!);
      this.matri$ = this.dam!.matriId && this.dam!.matriId > 0 ? this.getCow(this.dam!.matriId) : EMPTY;
      this.patri$ = this.dam!.patriId && this.dam!.patriId > 0 ? this.getCow(this.dam!.patriId) : EMPTY;
    });
  }

  private getArticle(storyHandle: string, currentLanguage: string): Observable<Maybe<ArticleVo>> {
    const i18nkey = Object.keys(I18n).filter(key => I18n[key] === currentLanguage)[0];
    return this.getArticleGQL
      .fetch({ i18n: I18n[i18nkey], isSummary: false, isHandle: true, id: storyHandle })
      .pipe(map(result => result.data.articleVO));
  }

  private getCow(earTagId: number): Observable<Maybe<CowVo>> {
    return this.getCowGQL.fetch({ earTagId }).pipe(map(result => result.data.cowVO));
  }

  private getCowPhotos(earTagId: number): Observable<Maybe<Array<Maybe<PhotographVo>>>> {
    setTimeout(() => this.loadingSubject.next(true));
    return this.findCowPhotosGQL.fetch({ earTagId }).pipe(
      map(result => result.data.apiPublicCowsPhotographs),
      map(photographs => {
        if (photographs && photographs.length) {
          return photographs;
        } else {
          return [DEFAULT_PHOTOGRAPH];
        }
      }),
      startWith([DEFAULT_PHOTOGRAPH]),
      finalize(() => this.loadingSubject.next(false))
    );
  }
}
