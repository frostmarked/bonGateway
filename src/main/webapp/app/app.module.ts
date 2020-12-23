import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { BonGatewayCoreModule } from 'app/core/core.module';
import { BonGatewayAppRoutingModule } from './app-routing.module';
import { BonGatewayHomeModule } from './home/home.module';
import { BonGatewayEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

import { BonPublicGraphQLModule } from 'app/bonpublicgraphql/bonpublicgraphql.module';
import { MarkdownModule } from 'ngx-markdown';

@NgModule({
  imports: [
    BrowserModule,
    BonGatewaySharedModule,
    BonGatewayCoreModule,
    BonGatewayHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    BonGatewayEntityModule,
    BonGatewayAppRoutingModule,
    BonPublicGraphQLModule,
    MarkdownModule.forRoot(),
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class BonGatewayAppModule {}
