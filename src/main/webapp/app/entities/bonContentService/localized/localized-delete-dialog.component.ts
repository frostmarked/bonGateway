import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILocalized } from 'app/shared/model/bonContentService/localized.model';
import { LocalizedService } from './localized.service';

@Component({
  templateUrl: './localized-delete-dialog.component.html',
})
export class LocalizedDeleteDialogComponent {
  localized?: ILocalized;

  constructor(protected localizedService: LocalizedService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.localizedService.delete(id).subscribe(() => {
      this.eventManager.broadcast('localizedListModification');
      this.activeModal.close();
    });
  }
}
