import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { BovineComponent } from './bovine.component';
import { BovineDetailComponent } from './bovine-detail.component';
import { bovineRoute } from './bovine.route';

@NgModule({
  imports: [BonGatewaySharedModule, RouterModule.forChild(bovineRoute)],
  declarations: [BovineComponent, BovineDetailComponent],
})
export class BonReplicaServiceBovineModule {}
