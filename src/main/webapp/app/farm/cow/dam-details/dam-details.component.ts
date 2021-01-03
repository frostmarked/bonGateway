import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleVo, Context, CowVo, FindCowPicturesGQL, GetArticleGQL, GetCowGQL, PictureVo } from 'app/bonpublicgraphql/bonpublicgraphql';
import { EMPTY, Observable } from 'rxjs';
import { JhiLanguageService } from 'ng-jhipster';
import { Maybe } from 'graphql/jsutils/Maybe';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { CowService } from 'app/farm/cow/cow.service';

@Component({
  selector: 'jhi-dam-details',
  templateUrl: './dam-details.component.html',
  styleUrls: ['./dam-details.component.scss'],
})
export class DamDetailsComponent implements OnInit {
  dam?: CowVo;
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
      this.dam = data.cowVo;

      this.pictures$ = this.cowService.getCowPictures(this.dam!.earTagId!);
      this.matri$ = this.cowService.getCow(this.dam!.matriId, Context.Parent);
      this.patri$ = this.cowService.getCow(this.dam!.patriId, Context.Parent);

      if (this.dam!.storyHandle) {
        this.article$ = this.cowService.getArticle(this.dam!.storyHandle, this.languageService.getCurrentLanguage());
        this.translateService.onLangChange.subscribe((event: LangChangeEvent) => {
          this.article$ = this.cowService.getArticle(this.dam!.storyHandle!, event.lang);
        });
      } else {
        this.article$ = EMPTY;
      }
    });
  }
}
