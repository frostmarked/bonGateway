import { Component, Input } from '@angular/core';
import { Maybe, ArticleVo } from 'app/bonpublicgraphql/bonpublicgraphql';

@Component({
  selector: 'jhi-cow-article',
  templateUrl: './cow-article.component.html',
  styleUrls: ['./cow-article.component.scss'],
})
export class CowArticleComponent {
  @Input()
  article?: Maybe<ArticleVo>;
}
