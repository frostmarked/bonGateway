import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { MatrilinealityComponent } from './matrilineality.component';
import { MatrilinealityDetailComponent } from './matrilineality-detail.component';
import { MatrilinealityUpdateComponent } from './matrilineality-update.component';
import { MatrilinealityDeleteDialogComponent } from './matrilineality-delete-dialog.component';
import { matrilinealityRoute } from './matrilineality.route';

@NgModule({
  imports: [BonGatewaySharedModule, RouterModule.forChild(matrilinealityRoute)],
  declarations: [
    MatrilinealityComponent,
    MatrilinealityDetailComponent,
    MatrilinealityUpdateComponent,
    MatrilinealityDeleteDialogComponent,
  ],
  entryComponents: [MatrilinealityDeleteDialogComponent],
})
export class BonLivestockServiceMatrilinealityModule {}
