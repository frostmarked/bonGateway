import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INote } from 'app/shared/model/bonLivestockService/note.model';

@Component({
  selector: 'jhi-note-detail',
  templateUrl: './note-detail.component.html',
})
export class NoteDetailComponent implements OnInit {
  note: INote | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ note }) => (this.note = note));
  }

  previousState(): void {
    window.history.back();
  }
}
