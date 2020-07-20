import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMatrilineality, Matrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';
import { MatrilinealityService } from './matrilineality.service';

@Component({
  selector: 'jhi-matrilineality-update',
  templateUrl: './matrilineality-update.component.html',
})
export class MatrilinealityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    familyname: [null, [Validators.required, Validators.maxLength(127)]],
    earTagId: [null, [Validators.required, Validators.min(0)]],
    name: [null, [Validators.required, Validators.maxLength(127)]],
    country: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(6)]],
    description: [null, [Validators.maxLength(1023)]],
    cattleNameRegexPattern: [null, [Validators.required, Validators.maxLength(255)]],
    patriId: [null, [Validators.required]],
    patriName: [null, [Validators.required, Validators.maxLength(127)]],
    patriCountry: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(6)]],
    polled: [null, [Validators.required]],
    storyHandle: [],
    visibility: [],
  });

  constructor(protected matrilinealityService: MatrilinealityService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ matrilineality }) => {
      this.updateForm(matrilineality);
    });
  }

  updateForm(matrilineality: IMatrilineality): void {
    this.editForm.patchValue({
      id: matrilineality.id,
      familyname: matrilineality.familyname,
      earTagId: matrilineality.earTagId,
      name: matrilineality.name,
      country: matrilineality.country,
      description: matrilineality.description,
      cattleNameRegexPattern: matrilineality.cattleNameRegexPattern,
      patriId: matrilineality.patriId,
      patriName: matrilineality.patriName,
      patriCountry: matrilineality.patriCountry,
      polled: matrilineality.polled,
      storyHandle: matrilineality.storyHandle,
      visibility: matrilineality.visibility,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const matrilineality = this.createFromForm();
    if (matrilineality.id !== undefined) {
      this.subscribeToSaveResponse(this.matrilinealityService.update(matrilineality));
    } else {
      this.subscribeToSaveResponse(this.matrilinealityService.create(matrilineality));
    }
  }

  private createFromForm(): IMatrilineality {
    return {
      ...new Matrilineality(),
      id: this.editForm.get(['id'])!.value,
      familyname: this.editForm.get(['familyname'])!.value,
      earTagId: this.editForm.get(['earTagId'])!.value,
      name: this.editForm.get(['name'])!.value,
      country: this.editForm.get(['country'])!.value,
      description: this.editForm.get(['description'])!.value,
      cattleNameRegexPattern: this.editForm.get(['cattleNameRegexPattern'])!.value,
      patriId: this.editForm.get(['patriId'])!.value,
      patriName: this.editForm.get(['patriName'])!.value,
      patriCountry: this.editForm.get(['patriCountry'])!.value,
      polled: this.editForm.get(['polled'])!.value,
      storyHandle: this.editForm.get(['storyHandle'])!.value,
      visibility: this.editForm.get(['visibility'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMatrilineality>>): void {
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
