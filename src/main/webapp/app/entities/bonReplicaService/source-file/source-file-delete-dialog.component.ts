import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISourceFile } from 'app/shared/model/bonReplicaService/source-file.model';
import { SourceFileService } from './source-file.service';

@Component({
  templateUrl: './source-file-delete-dialog.component.html',
})
export class SourceFileDeleteDialogComponent {
  sourceFile?: ISourceFile;

  constructor(
    protected sourceFileService: SourceFileService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sourceFileService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sourceFileListModification');
      this.activeModal.close();
    });
  }
}
