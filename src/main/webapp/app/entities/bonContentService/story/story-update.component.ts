import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStory, Story } from 'app/shared/model/bonContentService/story.model';
import { StoryService } from './story.service';

@Component({
  selector: 'jhi-story-update',
  templateUrl: './story-update.component.html',
})
export class StoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    category: [null, [Validators.required]],
    name: [null, [Validators.required, Validators.minLength(2)]],
    visibility: [],
  });

  constructor(protected storyService: StoryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ story }) => {
      this.updateForm(story);
    });
  }

  updateForm(story: IStory): void {
    this.editForm.patchValue({
      id: story.id,
      category: story.category,
      name: story.name,
      visibility: story.visibility,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const story = this.createFromForm();
    if (story.id !== undefined) {
      this.subscribeToSaveResponse(this.storyService.update(story));
    } else {
      this.subscribeToSaveResponse(this.storyService.create(story));
    }
  }

  private createFromForm(): IStory {
    return {
      ...new Story(),
      id: this.editForm.get(['id'])!.value,
      category: this.editForm.get(['category'])!.value,
      name: this.editForm.get(['name'])!.value,
      visibility: this.editForm.get(['visibility'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStory>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
