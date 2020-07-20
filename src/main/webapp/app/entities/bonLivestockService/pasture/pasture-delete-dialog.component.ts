import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPasture } from 'app/shared/model/bonLivestockService/pasture.model';
import { PastureService } from './pasture.service';

@Component({
  templateUrl: './pasture-delete-dialog.component.html',
})
export class PastureDeleteDialogComponent {
  pasture?: IPasture;

  constructor(protected pastureService: PastureService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.pastureService.delete(id).subscribe(() => {
      this.eventManager.broadcast('pastureListModification');
      this.activeModal.close();
    });
  }
}
