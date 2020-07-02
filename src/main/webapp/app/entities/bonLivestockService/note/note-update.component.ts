import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INote, Note } from 'app/shared/model/bonLivestockService/note.model';
import { NoteService } from './note.service';
import { IPasture } from 'app/shared/model/bonLivestockService/pasture.model';
import { PastureService } from 'app/entities/bonLivestockService/pasture/pasture.service';
import { ICattle } from 'app/shared/model/bonLivestockService/cattle.model';
import { CattleService } from 'app/entities/bonLivestockService/cattle/cattle.service';

type SelectableEntity = IPasture | ICattle;

@Component({
  selector: 'jhi-note-update',
  templateUrl: './note-update.component.html',
})
export class NoteUpdateComponent implements OnInit {
  isSaving = false;
  pastures: IPasture[] = [];
  cattles: ICattle[] = [];
  actualDateDp: any;

  editForm = this.fb.group({
    id: [],
    category: [null, [Validators.required]],
    note: [null, [Validators.maxLength(512)]],
    actualDate: [],
    pasture: [],
    cattle: [null, Validators.required],
  });

  constructor(
    protected noteService: NoteService,
    protected pastureService: PastureService,
    protected cattleService: CattleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ note }) => {
      this.updateForm(note);

      this.pastureService.query().subscribe((res: HttpResponse<IPasture[]>) => (this.pastures = res.body || []));

      this.cattleService.query().subscribe((res: HttpResponse<ICattle[]>) => (this.cattles = res.body || []));
    });
  }

  updateForm(note: INote): void {
    this.editForm.patchValue({
      id: note.id,
      category: note.category,
      note: note.note,
      actualDate: note.actualDate,
      pasture: note.pasture,
      cattle: note.cattle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const note = this.createFromForm();
    if (note.id !== undefined) {
      this.subscribeToSaveResponse(this.noteService.update(note));
    } else {
      this.subscribeToSaveResponse(this.noteService.create(note));
    }
  }

  private createFromForm(): INote {
    return {
      ...new Note(),
      id: this.editForm.get(['id'])!.value,
      category: this.editForm.get(['category'])!.value,
      note: this.editForm.get(['note'])!.value,
      actualDate: this.editForm.get(['actualDate'])!.value,
      pasture: this.editForm.get(['pasture'])!.value,
      cattle: this.editForm.get(['cattle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INote>>): void {
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
}
