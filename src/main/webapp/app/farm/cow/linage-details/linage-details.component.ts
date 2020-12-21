import { Component, OnInit } from '@angular/core';
import {
  FindCowPicturesGQL,
  LinageVo,
  FindCowsGQL,
  GetArticleGQL,
  ArticleVo,
  I18n,
  Maybe,
  PictureVo,
} from 'app/bonpublicgraphql/bonpublicgraphql';
import { DEFAULT_PICTURE, randomPictureVoFromPicsum } from 'app/shared/bon/picturevo-util';
import { map, startWith, finalize } from 'rxjs/operators';
import { Observable, BehaviorSubject } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';

interface DamItemVM {
  id: number;
  earTagId: number;
  familyname: string;
  visibility: string;
  polled: boolean;
  picture$: Observable<PictureVo>;
}

@Component({
  selector: 'jhi-linage-details',
  templateUrl: './linage-details.component.html',
  styleUrls: ['./linage-details.component.scss'],
})
export class LinageDetailsComponent implements OnInit {
  loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  linage?: LinageVo;
  article$?: Observable<Maybe<ArticleVo>>;
  dams$?: Observable<Array<DamItemVM>>;

  constructor(
    public activatedRoute: ActivatedRoute,
    private languageService: JhiLanguageService,
    private getArticleGQL: GetArticleGQL,
    private findCowsGQL: FindCowsGQL,
    private findCowPicturesGQL: FindCowPicturesGQL
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.linage = data.linageVo;
      if (this.linage!.storyHandle) {
        // TODO subscribe to changes to language?
        const curLang = this.languageService.getCurrentLanguage();
        this.article$ = this.getArticle(this.linage!.storyHandle, curLang);
      }
      this.dams$ = this.getDams(this.linage!.id!);
    });
  }

  private getArticle(storyHandle: string, currentLanguage: string): Observable<Maybe<ArticleVo>> {
    const i18nkey = Object.keys(I18n).filter(key => I18n[key] === currentLanguage)[0];
    return this.getArticleGQL
      .fetch({ i18n: I18n[i18nkey], isSummary: false, isHandle: true, id: storyHandle })
      .pipe(map(result => (result.data.articleVO ? result.data.articleVO : null)));
  }

  private getDams(linageId: number): Observable<Array<DamItemVM>> {
    setTimeout(() => this.loadingSubject.next(true));
    return this.findCowsGQL
      .fetch({ linageIdEquals: linageId, size: 100 }) // unlikly that there will be more then 30
      .pipe(
        map(result => result.data.apiPublicCows),
        map(cows =>
          cows!.map(
            cow =>
              ({
                earTagId: cow!.earTagId,
                visibility: cow!.visibility,
                picture$: this.getCowPicture(cow!.earTagId!),
              } as DamItemVM)
          )
        ),
        finalize(() => this.loadingSubject.next(false))
      );
  }

  private getCowPicture(earTagId: number): Observable<PictureVo> {
    return this.findCowPicturesGQL.fetch({ earTagId, size: 1 }).pipe(
      map(result => result.data.apiPublicCowsPictures),
      map(pics => (pics && pics[0] ? pics[0] : randomPictureVoFromPicsum('seed' + earTagId))),
      startWith(DEFAULT_PICTURE)
    );
  }
}
