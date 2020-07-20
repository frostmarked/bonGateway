import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMatrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';

@Component({
  selector: 'jhi-matrilineality-detail',
  templateUrl: './matrilineality-detail.component.html',
})
export class MatrilinealityDetailComponent implements OnInit {
  matrilineality: IMatrilineality | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ matrilineality }) => (this.matrilineality = matrilineality));
  }

  previousState(): void {
    window.history.back();
  }
}
