import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMatrilineality, Matrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';
import { MatrilinealityService } from './matrilineality.service';
import { MatrilinealityComponent } from './matrilineality.component';
import { MatrilinealityDetailComponent } from './matrilineality-detail.component';
import { MatrilinealityUpdateComponent } from './matrilineality-update.component';

@Injectable({ providedIn: 'root' })
export class MatrilinealityResolve implements Resolve<IMatrilineality> {
  constructor(private service: MatrilinealityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMatrilineality> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((matrilineality: HttpResponse<Matrilineality>) => {
          if (matrilineality.body) {
            return of(matrilineality.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Matrilineality());
  }
}

export const matrilinealityRoute: Routes = [
  {
    path: '',
    component: MatrilinealityComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'bonGatewayApp.bonLivestockServiceMatrilineality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MatrilinealityDetailComponent,
    resolve: {
      matrilineality: MatrilinealityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServiceMatrilineality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MatrilinealityUpdateComponent,
    resolve: {
      matrilineality: MatrilinealityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServiceMatrilineality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MatrilinealityUpdateComponent,
    resolve: {
      matrilineality: MatrilinealityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServiceMatrilineality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
