import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJournalEntry } from 'app/shared/model/bonReplicaService/journal-entry.model';

@Component({
  selector: 'jhi-journal-entry-detail',
  templateUrl: './journal-entry-detail.component.html',
})
export class JournalEntryDetailComponent implements OnInit {
  journalEntry: IJournalEntry | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ journalEntry }) => (this.journalEntry = journalEntry));
  }

  previousState(): void {
    window.history.back();
  }
}
