import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICattle } from 'app/shared/model/bonLivestockService/cattle.model';
import { CattleService } from './cattle.service';

@Component({
  templateUrl: './cattle-delete-dialog.component.html',
})
export class CattleDeleteDialogComponent {
  cattle?: ICattle;

  constructor(protected cattleService: CattleService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cattleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cattleListModification');
      this.activeModal.close();
    });
  }
}
