import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { ILocalized, Localized } from 'app/shared/model/bonContentService/localized.model';
import { LocalizedService } from './localized.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IFragment } from 'app/shared/model/bonContentService/fragment.model';
import { FragmentService } from 'app/entities/bonContentService/fragment/fragment.service';

@Component({
  selector: 'jhi-localized-update',
  templateUrl: './localized-update.component.html',
})
export class LocalizedUpdateComponent implements OnInit {
  isSaving = false;
  fragments: IFragment[] = [];

  editForm = this.fb.group({
    id: [],
    i18n: [null, [Validators.required, Validators.minLength(2), Validators.pattern('[a-z]+')]],
    title: [null, [Validators.required, Validators.maxLength(127)]],
    ingress: [null, [Validators.maxLength(255)]],
    body: [null, [Validators.required]],
    caption: [null, [Validators.maxLength(255)]],
    visibility: [],
    fragment: [null, Validators.required],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected localizedService: LocalizedService,
    protected fragmentService: FragmentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ localized }) => {
      this.updateForm(localized);

      this.fragmentService.query().subscribe((res: HttpResponse<IFragment[]>) => (this.fragments = res.body || []));
    });
  }

  updateForm(localized: ILocalized): void {
    this.editForm.patchValue({
      id: localized.id,
      i18n: localized.i18n,
      title: localized.title,
      ingress: localized.ingress,
      body: localized.body,
      caption: localized.caption,
      visibility: localized.visibility,
      fragment: localized.fragment,
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
    const localized = this.createFromForm();
    if (localized.id !== undefined) {
      this.subscribeToSaveResponse(this.localizedService.update(localized));
    } else {
      this.subscribeToSaveResponse(this.localizedService.create(localized));
    }
  }

  private createFromForm(): ILocalized {
    return {
      ...new Localized(),
      id: this.editForm.get(['id'])!.value,
      i18n: this.editForm.get(['i18n'])!.value,
      title: this.editForm.get(['title'])!.value,
      ingress: this.editForm.get(['ingress'])!.value,
      body: this.editForm.get(['body'])!.value,
      caption: this.editForm.get(['caption'])!.value,
      visibility: this.editForm.get(['visibility'])!.value,
      fragment: this.editForm.get(['fragment'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocalized>>): void {
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

  trackById(index: number, item: IFragment): any {
    return item.id;
  }
}
