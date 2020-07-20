import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { LocalizedComponent } from './localized.component';
import { LocalizedDetailComponent } from './localized-detail.component';
import { LocalizedUpdateComponent } from './localized-update.component';
import { LocalizedDeleteDialogComponent } from './localized-delete-dialog.component';
import { localizedRoute } from './localized.route';

@NgModule({
  imports: [BonGatewaySharedModule, RouterModule.forChild(localizedRoute)],
  declarations: [LocalizedComponent, LocalizedDetailComponent, LocalizedUpdateComponent, LocalizedDeleteDialogComponent],
  entryComponents: [LocalizedDeleteDialogComponent],
})
export class BonContentServiceLocalizedModule {}
