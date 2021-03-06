import { NgModule } from '@angular/core';
import { CowRoutingModule } from './cow-routing.module';
import { CowPictureDirective } from './cow-pictures.directive';
import { SireDetailsComponent } from './sire-details/sire-details.component';
import { SireListComponent } from './sire-list/sire-list.component';
import { DamListComponent } from './dam-list/dam-list.component';
import { DamDetailsComponent } from './dam-details/dam-details.component';
import { LinageListComponent } from './linage-list/linage-list.component';
import { LinageDetailsComponent } from './linage-details/linage-details.component';
import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { CowCardComponent } from './shared/cow-card/cow-card.component';
import { CowArticleComponent } from './shared/cow-article/cow-article.component';
import { MarkdownModule } from 'ngx-markdown';

@NgModule({
  declarations: [
    CowPictureDirective,
    SireDetailsComponent,
    SireListComponent,
    DamListComponent,
    DamDetailsComponent,
    LinageListComponent,
    LinageDetailsComponent,
    CowCardComponent,
    CowArticleComponent,
  ],
  imports: [BonGatewaySharedModule, CowRoutingModule, MarkdownModule.forChild()],
})
export class CowModule {}
