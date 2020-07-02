import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStory } from 'app/shared/model/bonContentService/story.model';

@Component({
  selector: 'jhi-story-detail',
  templateUrl: './story-detail.component.html',
})
export class StoryDetailComponent implements OnInit {
  story: IStory | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ story }) => (this.story = story));
  }

  previousState(): void {
    window.history.back();
  }
}
