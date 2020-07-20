import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { BlupComponent } from './blup.component';
import { BlupDetailComponent } from './blup-detail.component';
import { blupRoute } from './blup.route';

@NgModule({
  imports: [BonGatewaySharedModule, RouterModule.forChild(blupRoute)],
  declarations: [BlupComponent, BlupDetailComponent],
})
export class BonReplicaServiceBlupModule {}
