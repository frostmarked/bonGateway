import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IPhoto, Photo } from 'app/shared/model/bonLivestockService/photo.model';
import { PhotoService } from './photo.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { ICattle } from 'app/shared/model/bonLivestockService/cattle.model';
import { CattleService } from 'app/entities/bonLivestockService/cattle/cattle.service';
import { DropdownPaginationImpl, DropdownPagination } from 'app/shared/bon/dropdown-pagination-util';

@Component({
  selector: 'jhi-photo-update',
  templateUrl: './photo-update.component.html',
})
export class PhotoUpdateComponent implements OnInit {
  isSaving = false;
  cattleDropdownPagination: DropdownPagination;

  editForm = this.fb.group({
    id: [],
    image: [null, [Validators.required]],
    imageContentType: [],
    caption: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(140)]],
    height: [null, [Validators.min(0)]],
    width: [null, [Validators.min(0)]],
    taken: [],
    visibility: [],
    cattle: [null, Validators.required],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected photoService: PhotoService,
    protected cattleService: CattleService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {
    this.cattleDropdownPagination = new DropdownPaginationImpl(this.cattleService, 
      0, 50, 0, 0, ['earTagId,desc'], 0, [], undefined);
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ photo }) => {
      if (!photo.id) {
        const today = moment().startOf('day');
        photo.taken = today;
      }

      this.updateForm(photo);
         
      this.cattleDropdownPagination.selectItem(photo?.cattle);
      this.cattleDropdownPagination.load();
    });
  }

  updateForm(photo: IPhoto): void {
    this.editForm.patchValue({
      id: photo.id,
      image: photo.image,
      imageContentType: photo.imageContentType,
      caption: photo.caption,
      height: photo.height,
      width: photo.width,
      taken: photo.taken ? photo.taken.format(DATE_TIME_FORMAT) : null,
      visibility: photo.visibility,
      cattle: photo.cattle,
    });
    this.cattleDropdownPagination?.selectItem(photo.cattle);
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

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const photo = this.createFromForm();
    if (photo.id !== undefined) {
      this.subscribeToSaveResponse(this.photoService.update(photo));
    } else {
      this.subscribeToSaveResponse(this.photoService.create(photo));
    }
  }

  private createFromForm(): IPhoto {
    return {
      ...new Photo(),
      id: this.editForm.get(['id'])!.value,
      imageContentType: this.editForm.get(['imageContentType'])!.value,
      image: this.editForm.get(['image'])!.value,
      caption: this.editForm.get(['caption'])!.value,
      height: this.editForm.get(['height'])!.value,
      width: this.editForm.get(['width'])!.value,
      taken: this.editForm.get(['taken'])!.value ? moment(this.editForm.get(['taken'])!.value, DATE_TIME_FORMAT) : undefined,
      visibility: this.editForm.get(['visibility'])!.value,
      cattle: this.editForm.get(['cattle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPhoto>>): void {
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

  trackById(index: number, item: ICattle): any {
    return item.id;
  }
}
