import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ISourceFile, SourceFile } from 'app/shared/model/bonReplicaService/source-file.model';
import { SourceFileService } from './source-file.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-source-file-update',
  templateUrl: './source-file-update.component.html',
})
export class SourceFileUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(127)]],
    zipFile: [null, [Validators.required]],
    zipFileContentType: [],
    processed: [],
    outcome: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected sourceFileService: SourceFileService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sourceFile }) => {
      if (!sourceFile.id) {
        const today = moment().startOf('day');
        sourceFile.processed = today;
      }

      this.updateForm(sourceFile);
    });
  }

  updateForm(sourceFile: ISourceFile): void {
    this.editForm.patchValue({
      id: sourceFile.id,
      name: sourceFile.name,
      zipFile: sourceFile.zipFile,
      zipFileContentType: sourceFile.zipFileContentType,
      processed: sourceFile.processed ? sourceFile.processed.format(DATE_TIME_FORMAT) : null,
      outcome: sourceFile.outcome,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: any, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('bonGatewayApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sourceFile = this.createFromForm();
    if (sourceFile.id !== undefined) {
      this.subscribeToSaveResponse(this.sourceFileService.update(sourceFile));
    } else {
      this.subscribeToSaveResponse(this.sourceFileService.create(sourceFile));
    }
  }

  private createFromForm(): ISourceFile {
    return {
      ...new SourceFile(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      zipFileContentType: this.editForm.get(['zipFileContentType'])!.value,
      zipFile: this.editForm.get(['zipFile'])!.value,
      processed: this.editForm.get(['processed'])!.value ? moment(this.editForm.get(['processed'])!.value, DATE_TIME_FORMAT) : undefined,
      outcome: this.editForm.get(['outcome'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISourceFile>>): void {
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
