import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { SourceFileComponent } from './source-file.component';
import { SourceFileDetailComponent } from './source-file-detail.component';
import { SourceFileUpdateComponent } from './source-file-update.component';
import { SourceFileDeleteDialogComponent } from './source-file-delete-dialog.component';
import { sourceFileRoute } from './source-file.route';

@NgModule({
  imports: [BonGatewaySharedModule, RouterModule.forChild(sourceFileRoute)],
  declarations: [SourceFileComponent, SourceFileDetailComponent, SourceFileUpdateComponent, SourceFileDeleteDialogComponent],
  entryComponents: [SourceFileDeleteDialogComponent],
})
export class BonReplicaServiceSourceFileModule {}
