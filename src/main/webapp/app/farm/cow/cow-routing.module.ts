import { NgModule, Injectable } from '@angular/core';
import { Routes, RouterModule, Resolve, Router, ActivatedRouteSnapshot } from '@angular/router';
import { LinageListComponent } from './linage-list/linage-list.component';
import { DamDetailsComponent } from './dam-details/dam-details.component';
import { LinageDetailsComponent } from './linage-details/linage-details.component';
import { SireListComponent } from './sire-list/sire-list.component';
import { SireDetailsComponent } from './sire-details/sire-details.component';
import { LinageVo, GetLinageGQL, CowVo, GetCowGQL } from 'app/bonpublicgraphql/bonpublicgraphql';
import { Observable, EMPTY } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class LinageVoResolve implements Resolve<LinageVo> {
  constructor(private getLinageGQL: GetLinageGQL, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<LinageVo> | Observable<never> {
    const earTagId = route.params['earTagId'];
    if (earTagId) {
      return this.getLinageGQL.fetch({ earTagId }).pipe(
        map(result => {
          if (result.data.linageVO) {
            return result.data.linageVO;
          } else {
            this.router.navigate(['404']);
            return {};
          }
        })
      );
    } else {
      this.router.navigate(['404']);
      return EMPTY;
    }
  }
}

@Injectable({ providedIn: 'root' })
export class CowVoResolve implements Resolve<CowVo> {
  constructor(private getCowGQL: GetCowGQL, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<CowVo> | Observable<never> {
    const earTagId = route.params['earTagId'];
    if (earTagId) {
      return this.getCowGQL.fetch({ earTagId }).pipe(
        map(result => {
          if (result.data.cowVO) {
            return result.data.cowVO;
          } else {
            this.router.navigate(['404']);
            return {};
          }
        })
      );
    } else {
      this.router.navigate(['404']);
      return EMPTY;
    }
  }
}

const routes: Routes = [
  {
    path: 'linage',
    component: LinageListComponent,
  },
  {
    path: 'linage/:earTagId',
    component: LinageDetailsComponent,
    resolve: {
      linageVo: LinageVoResolve,
    },
  },
  {
    path: 'dam/:earTagId',
    component: DamDetailsComponent,
    resolve: {
      cowVo: CowVoResolve,
    },
  },
  {
    path: 'sire',
    component: SireListComponent,
  },
  {
    path: 'sire/:earTagId',
    component: SireDetailsComponent,
    resolve: {
      cowVo: CowVoResolve,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CowRoutingModule {}
