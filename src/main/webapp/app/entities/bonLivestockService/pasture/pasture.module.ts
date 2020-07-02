import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { PastureComponent } from './pasture.component';
import { PastureDetailComponent } from './pasture-detail.component';
import { PastureUpdateComponent } from './pasture-update.component';
import { PastureDeleteDialogComponent } from './pasture-delete-dialog.component';
import { pastureRoute } from './pasture.route';

@NgModule({
  imports: [BonGatewaySharedModule, RouterModule.forChild(pastureRoute)],
  declarations: [PastureComponent, PastureDetailComponent, PastureUpdateComponent, PastureDeleteDialogComponent],
  entryComponents: [PastureDeleteDialogComponent],
})
export class BonLivestockServicePastureModule {}
