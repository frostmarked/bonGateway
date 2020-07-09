import { Component, OnInit } from '@angular/core';
import { FindLinagesGQL, LinageVo } from 'app/bonpublicgraphql/bonpublicgraphql';
import { map } from 'rxjs/operators';
import { Maybe } from 'graphql/jsutils/Maybe';

@Component({
  selector: 'jhi-linage-list',
  templateUrl: './linage-list.component.html',
  styleUrls: ['./linage-list.component.scss'],
})
export class LinageListComponent implements OnInit {
  linages: Array<Maybe<LinageVo>> = [];

  constructor(private findLinagesGQL: FindLinagesGQL) {}

  ngOnInit(): void {
    this.findLinagesGQL
      .fetch({ size: 100 })
      .pipe(map(result => result.data.apiPublicLinages))
      .subscribe(list => (this.linages = list ? list : new Array<LinageVo>()));
  }
}
