import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CowRoutingModule } from './cow-routing.module';
import { SireDetailsComponent } from './sire-details/sire-details.component';
import { SireListComponent } from './sire-list/sire-list.component';
import { DamListComponent } from './dam-list/dam-list.component';
import { DamDetailsComponent } from './dam-details/dam-details.component';
import { LinageListComponent } from './linage-list/linage-list.component';
import { LinageDetailsComponent } from './linage-details/linage-details.component';
import { BonGatewaySharedModule } from 'app/shared/shared.module';

@NgModule({
  declarations: [
    SireDetailsComponent,
    SireListComponent,
    DamListComponent,
    DamDetailsComponent,
    LinageListComponent,
    LinageDetailsComponent,
  ],
  imports: [BonGatewaySharedModule, CommonModule, CowRoutingModule],
})
export class CowModule {}
