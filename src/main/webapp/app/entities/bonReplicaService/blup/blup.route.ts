import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBlup, Blup } from 'app/shared/model/bonReplicaService/blup.model';
import { BlupService } from './blup.service';
import { BlupComponent } from './blup.component';
import { BlupDetailComponent } from './blup-detail.component';

@Injectable({ providedIn: 'root' })
export class BlupResolve implements Resolve<IBlup> {
  constructor(private service: BlupService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBlup> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((blup: HttpResponse<Blup>) => {
          if (blup.body) {
            return of(blup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Blup());
  }
}

export const blupRoute: Routes = [
  {
    path: '',
    component: BlupComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'bonGatewayApp.bonReplicaServiceBlup.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BlupDetailComponent,
    resolve: {
      blup: BlupResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonReplicaServiceBlup.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
