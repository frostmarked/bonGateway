import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICattle } from '../model/bonLivestockService/cattle.model';
import { CattleService } from 'app/entities/bonLivestockService/cattle/cattle.service';
import { IBovine } from '../model/bonReplicaService/bovine.model';
import { BovineService } from 'app/entities/bonReplicaService/bovine/bovine.service';
import { FragmentService } from 'app/entities/bonContentService/fragment/fragment.service';
import { StoryService } from 'app/entities/bonContentService/story/story.service';
import { IFragment } from 'app/shared/model/bonContentService/fragment.model';
import { IStory } from 'app/shared/model/bonContentService/story.model';

// bummer... would like to use generics and interfaces
// but jhipster services dont have any and I dont want to change the generated code
// , or atleast as little as possible
type BonService = CattleService | BovineService | FragmentService | StoryService;
type BonEntity = ICattle | IBovine | IFragment | IStory;

export interface DropdownPagination {
  service: BonService;
  totalItems: number;
  itemsPerPage: number;
  page: number;
  pageToLoad: number;
  orderBy: string[];
  ngbPaginationPage: number;
  items: Array<BonEntity>;
  selectedItem?: BonEntity | undefined;

  load(page?: number): void;

  selectItem(selectedItem: BonEntity | undefined): void;

  createQueryParams(page?: number, sort?: string[]): any;

  onSuccess(res: HttpResponse<Array<BonEntity>>): void;

  onError(): void;
}

export class DropdownPaginationImpl implements DropdownPagination {
  constructor(
    public service: BonService,
    public totalItems: number,
    public itemsPerPage: number,
    public page: number,
    public pageToLoad: number,
    public orderBy: string[],
    public ngbPaginationPage: number,
    public items: Array<BonEntity>,
    public selectedItem?: BonEntity | undefined
  ) {}

  public load(page?: number): void {
    const obs: Observable<HttpResponse<BonEntity[]>> = this.service.query(this.createQueryParams(page));
    obs.subscribe(
      (res: HttpResponse<BonEntity[]>) => this.onSuccess(res),
      () => this.onError()
    );
  }

  public selectItem(selectedItem: BonEntity | undefined): void {
    this.selectedItem = selectedItem;
  }

  public createQueryParams(page?: number, sort?: string[]): any {
    this.pageToLoad = page || this.page || 1;
    if (sort && sort.length) {
      this.orderBy = sort;
    }
    return { page: this.pageToLoad - 1, size: this.itemsPerPage, sort: this.orderBy };
  }

  public onSuccess(res: HttpResponse<BonEntity[]>): void {
    this.totalItems = Number(res.headers.get('X-Total-Count'));
    this.page = this.pageToLoad;
    this.items = res.body || [];
    this.ngbPaginationPage = this.pageToLoad;

    if (this.selectedItem) {
      this.items.unshift(this.selectedItem);
    }
  }

  public onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
}
