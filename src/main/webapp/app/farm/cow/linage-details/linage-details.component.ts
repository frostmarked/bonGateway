import { Component, OnInit } from '@angular/core';
import { FindCowPicturesGQL, LinageVo, FindCowsGQL, GetArticleGQL, ArticleVo, PictureVo } from 'app/bonpublicgraphql/bonpublicgraphql';
import { map } from 'rxjs/operators';
import { EMPTY, Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { CowService } from 'app/farm/cow/cow.service';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { Maybe } from 'graphql/jsutils/Maybe';

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
  linage?: LinageVo;
  article$?: Observable<Maybe<ArticleVo>>;
  dams$?: Observable<Array<DamItemVM>>;

  constructor(
    public activatedRoute: ActivatedRoute,
    private languageService: JhiLanguageService,
    private getArticleGQL: GetArticleGQL,
    private findCowsGQL: FindCowsGQL,
    private findCowPicturesGQL: FindCowPicturesGQL,
    private translateService: TranslateService,
    private cowService: CowService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.linage = data.linageVo;
      this.dams$ = this.getDams(this.linage!.id!);
      if (this.linage!.storyHandle) {
        this.article$ = this.cowService.getArticle(this.linage!.storyHandle, this.languageService.getCurrentLanguage());
        this.translateService.onLangChange.subscribe((event: LangChangeEvent) => {
          this.article$ = this.cowService.getArticle(this.linage!.storyHandle!, event.lang);
        });
      } else {
        this.article$ = EMPTY;
      }
    });
  }

  private getDams(linageId: number): Observable<Array<DamItemVM>> {
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
                picture$: this.cowService.getFirstCowPicture(cow!.earTagId!),
              } as DamItemVM)
          )
        )
      );
  }
}
