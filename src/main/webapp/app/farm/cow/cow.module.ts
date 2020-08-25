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

@NgModule({
  declarations: [
    CowPictureDirective,
    SireDetailsComponent,
    SireListComponent,
    DamListComponent,
    DamDetailsComponent,
    LinageListComponent,
    LinageDetailsComponent,
  ],
  imports: [BonGatewaySharedModule, CowRoutingModule],
})
export class CowModule {}
