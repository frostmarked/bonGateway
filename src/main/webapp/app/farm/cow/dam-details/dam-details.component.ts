import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CowVo } from 'app/bonpublicgraphql/bonpublicgraphql';

@Component({
  selector: 'jhi-dam-details',
  templateUrl: './dam-details.component.html',
  styleUrls: ['./dam-details.component.scss'],
})
export class DamDetailsComponent implements OnInit {
  cowVo?: CowVo;

  constructor(public activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.cowVo = data.cowVo;
    });
  }
}
