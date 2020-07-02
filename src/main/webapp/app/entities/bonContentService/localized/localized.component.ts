import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILocalized } from 'app/shared/model/bonContentService/localized.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { LocalizedService } from './localized.service';
import { LocalizedDeleteDialogComponent } from './localized-delete-dialog.component';

@Component({
  selector: 'jhi-localized',
  templateUrl: './localized.component.html',
})
export class LocalizedComponent implements OnInit, OnDestroy {
  localizeds: ILocalized[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;
  currentSearch: string;

  constructor(
    protected localizedService: LocalizedService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute
  ) {
    this.localizeds = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll(): void {
    if (this.currentSearch) {
      this.localizedService
        .search({
          query: this.currentSearch,
          page: this.page,
          size: this.itemsPerPage,
          sort: this.sort(),
        })
        .subscribe((res: HttpResponse<ILocalized[]>) => this.paginateLocalizeds(res.body, res.headers));
      return;
    }

    this.localizedService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<ILocalized[]>) => this.paginateLocalizeds(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.localizeds = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  search(query: string): void {
    this.localizeds = [];
    this.links = {
      last: 0,
    };
    this.page = 0;
    if (query) {
      this.predicate = '_score';
      this.ascending = false;
    } else {
      this.predicate = 'id';
      this.ascending = true;
    }
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLocalizeds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILocalized): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInLocalizeds(): void {
    this.eventSubscriber = this.eventManager.subscribe('localizedListModification', () => this.reset());
  }

  delete(localized: ILocalized): void {
    const modalRef = this.modalService.open(LocalizedDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.localized = localized;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateLocalizeds(data: ILocalized[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.localizeds.push(data[i]);
      }
    }
  }
}
