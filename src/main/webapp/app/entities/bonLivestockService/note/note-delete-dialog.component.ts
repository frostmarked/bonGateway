import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INote } from 'app/shared/model/bonLivestockService/note.model';
import { NoteService } from './note.service';

@Component({
  templateUrl: './note-delete-dialog.component.html',
})
export class NoteDeleteDialogComponent {
  note?: INote;

  constructor(protected noteService: NoteService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.noteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('noteListModification');
      this.activeModal.close();
    });
  }
}
