import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILocalized, Localized } from 'app/shared/model/bonContentService/localized.model';
import { LocalizedService } from './localized.service';
import { LocalizedComponent } from './localized.component';
import { LocalizedDetailComponent } from './localized-detail.component';
import { LocalizedUpdateComponent } from './localized-update.component';

@Injectable({ providedIn: 'root' })
export class LocalizedResolve implements Resolve<ILocalized> {
  constructor(private service: LocalizedService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILocalized> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((localized: HttpResponse<Localized>) => {
          if (localized.body) {
            return of(localized.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Localized());
  }
}

export const localizedRoute: Routes = [
  {
    path: '',
    component: LocalizedComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceLocalized.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LocalizedDetailComponent,
    resolve: {
      localized: LocalizedResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceLocalized.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LocalizedUpdateComponent,
    resolve: {
      localized: LocalizedResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceLocalized.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LocalizedUpdateComponent,
    resolve: {
      localized: LocalizedResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceLocalized.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
