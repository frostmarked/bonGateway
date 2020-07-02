import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ILocalized } from 'app/shared/model/bonContentService/localized.model';

@Component({
  selector: 'jhi-localized-detail',
  templateUrl: './localized-detail.component.html',
})
export class LocalizedDetailComponent implements OnInit {
  localized: ILocalized | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ localized }) => (this.localized = localized));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
