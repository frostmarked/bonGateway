import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { FragmentComponent } from './fragment.component';
import { FragmentDetailComponent } from './fragment-detail.component';
import { FragmentUpdateComponent } from './fragment-update.component';
import { FragmentDeleteDialogComponent } from './fragment-delete-dialog.component';
import { fragmentRoute } from './fragment.route';

@NgModule({
  imports: [BonGatewaySharedModule, RouterModule.forChild(fragmentRoute)],
  declarations: [FragmentComponent, FragmentDetailComponent, FragmentUpdateComponent, FragmentDeleteDialogComponent],
  entryComponents: [FragmentDeleteDialogComponent],
})
export class BonContentServiceFragmentModule {}
