import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICattle, Cattle } from 'app/shared/model/bonLivestockService/cattle.model';
import { CattleService } from './cattle.service';
import { CattleComponent } from './cattle.component';
import { CattleDetailComponent } from './cattle-detail.component';
import { CattleUpdateComponent } from './cattle-update.component';

@Injectable({ providedIn: 'root' })
export class CattleResolve implements Resolve<ICattle> {
  constructor(private service: CattleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICattle> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cattle: HttpResponse<Cattle>) => {
          if (cattle.body) {
            return of(cattle.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Cattle());
  }
}

export const cattleRoute: Routes = [
  {
    path: '',
    component: CattleComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'bonGatewayApp.bonLivestockServiceCattle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CattleDetailComponent,
    resolve: {
      cattle: CattleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServiceCattle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CattleUpdateComponent,
    resolve: {
      cattle: CattleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServiceCattle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CattleUpdateComponent,
    resolve: {
      cattle: CattleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServiceCattle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
