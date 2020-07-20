import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IFragment, Fragment } from 'app/shared/model/bonContentService/fragment.model';
import { FragmentService } from './fragment.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { ITag } from 'app/shared/model/bonContentService/tag.model';
import { TagService } from 'app/entities/bonContentService/tag/tag.service';
import { IStory } from 'app/shared/model/bonContentService/story.model';
import { StoryService } from 'app/entities/bonContentService/story/story.service';

type SelectableEntity = ITag | IStory;

@Component({
  selector: 'jhi-fragment-update',
  templateUrl: './fragment-update.component.html',
})
export class FragmentUpdateComponent implements OnInit {
  isSaving = false;
  tags: ITag[] = [];
  stories: IStory[] = [];

  editForm = this.fb.group({
    id: [],
    template: [null, [Validators.required]],
    name: [null, [Validators.required, Validators.minLength(2)]],
    title: [null, [Validators.maxLength(127)]],
    ingress: [null, [Validators.maxLength(255)]],
    body: [],
    image: [],
    imageContentType: [],
    caption: [null, [Validators.maxLength(255)]],
    width: [],
    height: [],
    orderNo: [null, [Validators.required]],
    visibility: [],
    tags: [],
    story: [null, Validators.required],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected fragmentService: FragmentService,
    protected tagService: TagService,
    protected storyService: StoryService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fragment }) => {
      this.updateForm(fragment);

      this.tagService.query().subscribe((res: HttpResponse<ITag[]>) => (this.tags = res.body || []));

      this.storyService.query().subscribe((res: HttpResponse<IStory[]>) => (this.stories = res.body || []));
    });
  }

  updateForm(fragment: IFragment): void {
    this.editForm.patchValue({
      id: fragment.id,
      template: fragment.template,
      name: fragment.name,
      title: fragment.title,
      ingress: fragment.ingress,
      body: fragment.body,
      image: fragment.image,
      imageContentType: fragment.imageContentType,
      caption: fragment.caption,
      width: fragment.width,
      height: fragment.height,
      orderNo: fragment.orderNo,
      visibility: fragment.visibility,
      tags: fragment.tags,
      story: fragment.story,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
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
    const fragment = this.createFromForm();
    if (fragment.id !== undefined) {
      this.subscribeToSaveResponse(this.fragmentService.update(fragment));
    } else {
      this.subscribeToSaveResponse(this.fragmentService.create(fragment));
    }
  }

  private createFromForm(): IFragment {
    return {
      ...new Fragment(),
      id: this.editForm.get(['id'])!.value,
      template: this.editForm.get(['template'])!.value,
      name: this.editForm.get(['name'])!.value,
      title: this.editForm.get(['title'])!.value,
      ingress: this.editForm.get(['ingress'])!.value,
      body: this.editForm.get(['body'])!.value,
      imageContentType: this.editForm.get(['imageContentType'])!.value,
      image: this.editForm.get(['image'])!.value,
      caption: this.editForm.get(['caption'])!.value,
      width: this.editForm.get(['width'])!.value,
      height: this.editForm.get(['height'])!.value,
      orderNo: this.editForm.get(['orderNo'])!.value,
      visibility: this.editForm.get(['visibility'])!.value,
      tags: this.editForm.get(['tags'])!.value,
      story: this.editForm.get(['story'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFragment>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: ITag[], option: ITag): ITag {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
