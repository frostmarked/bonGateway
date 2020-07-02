import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICattle, Cattle } from 'app/shared/model/bonLivestockService/cattle.model';
import { CattleService } from './cattle.service';
import { IMatrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';
import { MatrilinealityService } from 'app/entities/bonLivestockService/matrilineality/matrilineality.service';

@Component({
  selector: 'jhi-cattle-update',
  templateUrl: './cattle-update.component.html',
})
export class CattleUpdateComponent implements OnInit {
  isSaving = false;
  matrilinealities: IMatrilineality[] = [];

  editForm = this.fb.group({
    id: [],
    earTagId: [null, [Validators.required, Validators.min(0)]],
    name: [null, [Validators.required, Validators.maxLength(127)]],
    visibility: [],
    upForSale: [null, [Validators.required]],
    showBlup: [null, [Validators.required]],
    alert: [null, [Validators.required]],
    storyHandle: [],
    matrilineality: [],
  });

  constructor(
    protected cattleService: CattleService,
    protected matrilinealityService: MatrilinealityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cattle }) => {
      this.updateForm(cattle);

      this.matrilinealityService.query().subscribe((res: HttpResponse<IMatrilineality[]>) => (this.matrilinealities = res.body || []));
    });
  }

  updateForm(cattle: ICattle): void {
    this.editForm.patchValue({
      id: cattle.id,
      earTagId: cattle.earTagId,
      name: cattle.name,
      visibility: cattle.visibility,
      upForSale: cattle.upForSale,
      showBlup: cattle.showBlup,
      alert: cattle.alert,
      storyHandle: cattle.storyHandle,
      matrilineality: cattle.matrilineality,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cattle = this.createFromForm();
    if (cattle.id !== undefined) {
      this.subscribeToSaveResponse(this.cattleService.update(cattle));
    } else {
      this.subscribeToSaveResponse(this.cattleService.create(cattle));
    }
  }

  private createFromForm(): ICattle {
    return {
      ...new Cattle(),
      id: this.editForm.get(['id'])!.value,
      earTagId: this.editForm.get(['earTagId'])!.value,
      name: this.editForm.get(['name'])!.value,
      visibility: this.editForm.get(['visibility'])!.value,
      upForSale: this.editForm.get(['upForSale'])!.value,
      showBlup: this.editForm.get(['showBlup'])!.value,
      alert: this.editForm.get(['alert'])!.value,
      storyHandle: this.editForm.get(['storyHandle'])!.value,
      matrilineality: this.editForm.get(['matrilineality'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICattle>>): void {
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

  trackById(index: number, item: IMatrilineality): any {
    return item.id;
  }
}
