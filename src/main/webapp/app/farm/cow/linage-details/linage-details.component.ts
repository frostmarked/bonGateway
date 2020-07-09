import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LinageVo, FindCowsGQL, CowVo, Maybe } from 'app/bonpublicgraphql/bonpublicgraphql';
import { map } from 'rxjs/operators';

@Component({
  selector: 'jhi-linage-details',
  templateUrl: './linage-details.component.html',
  styleUrls: ['./linage-details.component.scss'],
})
export class LinageDetailsComponent implements OnInit {
  linageVo?: LinageVo;
  cowVos?: Array<Maybe<CowVo>>;

  constructor(public activatedRoute: ActivatedRoute, private findCowsGQL: FindCowsGQL) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.linageVo = data.linageVo;
      this.findCowsGQL
        .fetch({ linageIdEquals: this.linageVo?.id, size: 100 })
        .pipe(map(result => result.data.apiPublicCows))
        .subscribe(list => (this.cowVos = list ? list : new Array<CowVo>()));
    });
  }
}
