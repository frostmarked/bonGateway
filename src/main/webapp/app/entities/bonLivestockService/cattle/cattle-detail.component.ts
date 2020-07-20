import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICattle } from 'app/shared/model/bonLivestockService/cattle.model';

@Component({
  selector: 'jhi-cattle-detail',
  templateUrl: './cattle-detail.component.html',
})
export class CattleDetailComponent implements OnInit {
  cattle: ICattle | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cattle }) => (this.cattle = cattle));
  }

  previousState(): void {
    window.history.back();
  }
}
