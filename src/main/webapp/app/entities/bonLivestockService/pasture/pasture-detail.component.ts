import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPasture } from 'app/shared/model/bonLivestockService/pasture.model';

@Component({
  selector: 'jhi-pasture-detail',
  templateUrl: './pasture-detail.component.html',
})
export class PastureDetailComponent implements OnInit {
  pasture: IPasture | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pasture }) => (this.pasture = pasture));
  }

  previousState(): void {
    window.history.back();
  }
}
