import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISourceFile } from 'app/shared/model/bonReplicaService/source-file.model';

@Component({
  selector: 'jhi-source-file-detail',
  templateUrl: './source-file-detail.component.html',
})
export class SourceFileDetailComponent implements OnInit {
  sourceFile: ISourceFile | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sourceFile }) => (this.sourceFile = sourceFile));
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
