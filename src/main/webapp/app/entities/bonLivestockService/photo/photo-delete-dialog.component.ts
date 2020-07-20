import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPhoto } from 'app/shared/model/bonLivestockService/photo.model';
import { PhotoService } from './photo.service';

@Component({
  templateUrl: './photo-delete-dialog.component.html',
})
export class PhotoDeleteDialogComponent {
  photo?: IPhoto;

  constructor(protected photoService: PhotoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.photoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('photoListModification');
      this.activeModal.close();
    });
  }
}
