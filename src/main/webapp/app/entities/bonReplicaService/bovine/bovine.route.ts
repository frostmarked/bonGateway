import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBovine, Bovine } from 'app/shared/model/bonReplicaService/bovine.model';
import { BovineService } from './bovine.service';
import { BovineComponent } from './bovine.component';
import { BovineDetailComponent } from './bovine-detail.component';

@Injectable({ providedIn: 'root' })
export class BovineResolve implements Resolve<IBovine> {
  constructor(private service: BovineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBovine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bovine: HttpResponse<Bovine>) => {
          if (bovine.body) {
            return of(bovine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bovine());
  }
}

export const bovineRoute: Routes = [
  {
    path: '',
    component: BovineComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'bonGatewayApp.bonReplicaServiceBovine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BovineDetailComponent,
    resolve: {
      bovine: BovineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonReplicaServiceBovine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
