import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { CattleComponent } from './cattle.component';
import { CattleDetailComponent } from './cattle-detail.component';
import { CattleUpdateComponent } from './cattle-update.component';
import { CattleDeleteDialogComponent } from './cattle-delete-dialog.component';
import { cattleRoute } from './cattle.route';

@NgModule({
  imports: [BonGatewaySharedModule, RouterModule.forChild(cattleRoute)],
  declarations: [CattleComponent, CattleDetailComponent, CattleUpdateComponent, CattleDeleteDialogComponent],
  entryComponents: [CattleDeleteDialogComponent],
})
export class BonLivestockServiceCattleModule {}
