import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FindCowPicturesGQL, GetArticleGQL, ArticleVo, CowVo, GetCowGQL, PictureVo } from 'app/bonpublicgraphql/bonpublicgraphql';
import { Observable, EMPTY } from 'rxjs';
import { JhiLanguageService } from 'ng-jhipster';
import { Maybe } from 'graphql/jsutils/Maybe';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { CowService } from 'app/farm/cow/cow.service';

@Component({
  selector: 'jhi-sire-details',
  templateUrl: './sire-details.component.html',
  styleUrls: ['./sire-details.component.scss'],
})
export class SireDetailsComponent implements OnInit {
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
    private findCowPicturesGQL: FindCowPicturesGQL,
    private translateService: TranslateService,
    private cowService: CowService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.sire = data.cowVo;

      this.pictures$ = this.cowService.getCowPictures(this.sire!.earTagId!);
      this.matri$ = this.cowService.getCow(this.sire!.matriId);
      this.patri$ = this.cowService.getCow(this.sire!.patriId);

      if (this.sire!.storyHandle) {
        this.article$ = this.cowService.getArticle(this.sire!.storyHandle, this.languageService.getCurrentLanguage());
        this.translateService.onLangChange.subscribe((event: LangChangeEvent) => {
          this.article$ = this.cowService.getArticle(this.sire!.storyHandle!, event.lang);
        });
      } else {
        this.article$ = EMPTY;
      }
    });
  }
}
