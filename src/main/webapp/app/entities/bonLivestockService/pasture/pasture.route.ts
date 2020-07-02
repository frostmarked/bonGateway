import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPasture, Pasture } from 'app/shared/model/bonLivestockService/pasture.model';
import { PastureService } from './pasture.service';
import { PastureComponent } from './pasture.component';
import { PastureDetailComponent } from './pasture-detail.component';
import { PastureUpdateComponent } from './pasture-update.component';

@Injectable({ providedIn: 'root' })
export class PastureResolve implements Resolve<IPasture> {
  constructor(private service: PastureService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPasture> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((pasture: HttpResponse<Pasture>) => {
          if (pasture.body) {
            return of(pasture.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Pasture());
  }
}

export const pastureRoute: Routes = [
  {
    path: '',
    component: PastureComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'bonGatewayApp.bonLivestockServicePasture.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PastureDetailComponent,
    resolve: {
      pasture: PastureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServicePasture.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PastureUpdateComponent,
    resolve: {
      pasture: PastureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServicePasture.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PastureUpdateComponent,
    resolve: {
      pasture: PastureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServicePasture.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
