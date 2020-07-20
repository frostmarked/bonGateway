import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPasture, Pasture } from 'app/shared/model/bonLivestockService/pasture.model';
import { PastureService } from './pasture.service';

@Component({
  selector: 'jhi-pasture-update',
  templateUrl: './pasture-update.component.html',
})
export class PastureUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(127)]],
    description: [null, [Validators.required, Validators.maxLength(1023)]],
  });

  constructor(protected pastureService: PastureService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pasture }) => {
      this.updateForm(pasture);
    });
  }

  updateForm(pasture: IPasture): void {
    this.editForm.patchValue({
      id: pasture.id,
      name: pasture.name,
      description: pasture.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pasture = this.createFromForm();
    if (pasture.id !== undefined) {
      this.subscribeToSaveResponse(this.pastureService.update(pasture));
    } else {
      this.subscribeToSaveResponse(this.pastureService.create(pasture));
    }
  }

  private createFromForm(): IPasture {
    return {
      ...new Pasture(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPasture>>): void {
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
