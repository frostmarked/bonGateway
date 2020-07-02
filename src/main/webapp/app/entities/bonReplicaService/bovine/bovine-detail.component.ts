import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBovine } from 'app/shared/model/bonReplicaService/bovine.model';

@Component({
  selector: 'jhi-bovine-detail',
  templateUrl: './bovine-detail.component.html',
})
export class BovineDetailComponent implements OnInit {
  bovine: IBovine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bovine }) => (this.bovine = bovine));
  }

  previousState(): void {
    window.history.back();
  }
}
