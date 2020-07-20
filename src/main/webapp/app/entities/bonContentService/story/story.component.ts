import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IStory } from 'app/shared/model/bonContentService/story.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { StoryService } from './story.service';
import { StoryDeleteDialogComponent } from './story-delete-dialog.component';

@Component({
  selector: 'jhi-story',
  templateUrl: './story.component.html',
})
export class StoryComponent implements OnInit, OnDestroy {
  stories: IStory[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected storyService: StoryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.stories = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.storyService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IStory[]>) => this.paginateStories(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.stories = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInStories();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IStory): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInStories(): void {
    this.eventSubscriber = this.eventManager.subscribe('storyListModification', () => this.reset());
  }

  delete(story: IStory): void {
    const modalRef = this.modalService.open(StoryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.story = story;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateStories(data: IStory[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.stories.push(data[i]);
      }
    }
  }
}
