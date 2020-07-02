import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBlup } from 'app/shared/model/bonReplicaService/blup.model';

@Component({
  selector: 'jhi-blup-detail',
  templateUrl: './blup-detail.component.html',
})
export class BlupDetailComponent implements OnInit {
  blup: IBlup | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ blup }) => (this.blup = blup));
  }

  previousState(): void {
    window.history.back();
  }
}
