import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStory } from 'app/shared/model/bonContentService/story.model';
import { StoryService } from './story.service';

@Component({
  templateUrl: './story-delete-dialog.component.html',
})
export class StoryDeleteDialogComponent {
  story?: IStory;

  constructor(protected storyService: StoryService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.storyService.delete(id).subscribe(() => {
      this.eventManager.broadcast('storyListModification');
      this.activeModal.close();
    });
  }
}
