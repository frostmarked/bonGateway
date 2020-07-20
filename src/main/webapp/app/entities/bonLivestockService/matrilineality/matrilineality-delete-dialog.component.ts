import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMatrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';
import { MatrilinealityService } from './matrilineality.service';

@Component({
  templateUrl: './matrilineality-delete-dialog.component.html',
})
export class MatrilinealityDeleteDialogComponent {
  matrilineality?: IMatrilineality;

  constructor(
    protected matrilinealityService: MatrilinealityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.matrilinealityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('matrilinealityListModification');
      this.activeModal.close();
    });
  }
}
