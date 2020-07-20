import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BonGatewaySharedModule } from 'app/shared/shared.module';
import { JournalEntryComponent } from './journal-entry.component';
import { JournalEntryDetailComponent } from './journal-entry-detail.component';
import { journalEntryRoute } from './journal-entry.route';

@NgModule({
  imports: [BonGatewaySharedModule, RouterModule.forChild(journalEntryRoute)],
  declarations: [JournalEntryComponent, JournalEntryDetailComponent],
})
export class BonReplicaServiceJournalEntryModule {}
