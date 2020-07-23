import { Component, OnInit } from '@angular/core';
import { FindCowPhotosGQL, LinageVo, FindCowsGQL, GetArticleGQL, ArticleVo, I18n } from 'app/bonpublicgraphql/bonpublicgraphql';
import { map, startWith, finalize } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Maybe } from 'graphql/jsutils/Maybe';

interface DamItemVM {
  id: number;
  earTagId: number;
  familyname: string;
  visibility: string;
  polled: boolean;
  photo$: Observable<string>;
}

const DEFAULT_IMG = '/content/images/bon/simple-cow-logo-limousin.png';

@Component({
  selector: 'jhi-linage-details',
  templateUrl: './linage-details.component.html',
  styleUrls: ['./linage-details.component.scss'],
})
export class LinageDetailsComponent implements OnInit {
  loading = true;
  linage?: LinageVo;
  article$?: Observable<Maybe<ArticleVo>>;
  dams$?: Observable<Array<DamItemVM>>;

  constructor(
    public activatedRoute: ActivatedRoute,
    private getArticleGQL: GetArticleGQL,
    private findCowsGQL: FindCowsGQL,
    private findCowPhotosGQL: FindCowPhotosGQL
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.linage = data.linageVo;
      if (this.linage!.storyHandle) {
        this.article$ = this.getArticle(this.linage!.storyHandle);
      }
      this.dams$ = this.getDams(this.linage!.id!);
    });
  }

  private getArticle(storyHandle: string): Observable<Maybe<ArticleVo>> {
    return this.getArticleGQL
      .fetch({ i18n: I18n.En, isSummary: false, isHandle: true, id: storyHandle })
      .pipe(map(result => result.data.articleVO));
  }

  private getDams(linageId: number): Observable<Array<DamItemVM>> {
    this.loading = true;
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
                photo$: this.getCowPhoto(cow!.earTagId!),
              } as DamItemVM)
          )
        ),
        finalize(() => (this.loading = false))
      );
  }

  // use default image until actual photo is fetched
  // or return default if none exists
  private getCowPhoto(earTagId: number): Observable<string> {
    return this.findCowPhotosGQL.fetch({ earTagId, size: 1 }).pipe(
      map(result => result.data.apiPublicCowsPhotographs),
      map(photos => {
        if (photos && photos[0]) {
          return `data:${photos[0].imageContentType};base64,${photos[0].image}`;
        } else {
          return DEFAULT_IMG;
        }
      }),
      startWith(DEFAULT_IMG)
    );
  }
}
