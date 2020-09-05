import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FindCowPicturesGQL, GetArticleGQL, ArticleVo, I18n, CowVo, GetCowGQL, PictureVo } from 'app/bonpublicgraphql/bonpublicgraphql';
import { map, finalize, startWith } from 'rxjs/operators';
import { Observable, BehaviorSubject, EMPTY } from 'rxjs';
import { JhiLanguageService } from 'ng-jhipster';
import { Maybe } from 'graphql/jsutils/Maybe';
import { DEFAULT_PICTURE } from 'app/shared/bon/picturevo-util';

@Component({
  selector: 'jhi-sire-details',
  templateUrl: './sire-details.component.html',
  styleUrls: ['./sire-details.component.scss'],
})
export class SireDetailsComponent implements OnInit {
  loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  sire?: CowVo;
  article$?: Observable<Maybe<ArticleVo>>;
  pictures$?: Observable<Maybe<Array<Maybe<PictureVo>>>>;
  matri$?: Observable<Maybe<CowVo>>;
  patri$?: Observable<Maybe<CowVo>>;

  constructor(
    public activatedRoute: ActivatedRoute,
    private languageService: JhiLanguageService,
    private getArticleGQL: GetArticleGQL,
    private getCowGQL: GetCowGQL,
    private findCowPicturesGQL: FindCowPicturesGQL
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.sire = data.cowVo;
      this.article$ = this.sire!.storyHandle ? this.getArticle(this.sire!.storyHandle, this.languageService.getCurrentLanguage()) : EMPTY;
      this.pictures$ = this.getCowPictures(this.sire!.earTagId!);
      this.matri$ = this.sire!.matriId && this.sire!.matriId > 0 ? this.getCow(this.sire!.matriId) : EMPTY;
      this.patri$ = this.sire!.patriId && this.sire!.patriId > 0 ? this.getCow(this.sire!.patriId) : EMPTY;
    });
  }

  private getArticle(storyHandle: string, currentLanguage: string): Observable<Maybe<ArticleVo>> {
    const i18nkey = Object.keys(I18n).filter(key => I18n[key] === currentLanguage)[0];
    return this.getArticleGQL
      .fetch({ i18n: I18n[i18nkey], isSummary: false, isHandle: true, id: storyHandle })
      .pipe(map(result => result.data.articleVO));
  }

  private getCow(earTagId: number): Observable<Maybe<CowVo>> {
    return this.getCowGQL.fetch({ earTagId }).pipe(
      map(result => result.data.cowVO),
      finalize(() => this.loadingSubject.next(false))
    );
  }

  private getCowPictures(earTagId: number): Observable<Maybe<Array<Maybe<PictureVo>>>> {
    return this.findCowPicturesGQL.fetch({ earTagId }).pipe(
      map(result => result.data.apiPublicCowsPictures),
      map(pics => {
        if (pics && pics.length) {
          return pics;
        } else {
          return [DEFAULT_PICTURE];
        }
      }),
      startWith([DEFAULT_PICTURE])
    );
  }
}
