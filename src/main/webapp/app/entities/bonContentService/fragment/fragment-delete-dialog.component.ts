import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFragment } from 'app/shared/model/bonContentService/fragment.model';
import { FragmentService } from './fragment.service';

@Component({
  templateUrl: './fragment-delete-dialog.component.html',
})
export class FragmentDeleteDialogComponent {
  fragment?: IFragment;

  constructor(protected fragmentService: FragmentService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fragmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fragmentListModification');
      this.activeModal.close();
    });
  }
}
