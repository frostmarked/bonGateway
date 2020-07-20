import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFragment, Fragment } from 'app/shared/model/bonContentService/fragment.model';
import { FragmentService } from './fragment.service';
import { FragmentComponent } from './fragment.component';
import { FragmentDetailComponent } from './fragment-detail.component';
import { FragmentUpdateComponent } from './fragment-update.component';

@Injectable({ providedIn: 'root' })
export class FragmentResolve implements Resolve<IFragment> {
  constructor(private service: FragmentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFragment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fragment: HttpResponse<Fragment>) => {
          if (fragment.body) {
            return of(fragment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Fragment());
  }
}

export const fragmentRoute: Routes = [
  {
    path: '',
    component: FragmentComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceFragment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FragmentDetailComponent,
    resolve: {
      fragment: FragmentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceFragment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FragmentUpdateComponent,
    resolve: {
      fragment: FragmentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceFragment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FragmentUpdateComponent,
    resolve: {
      fragment: FragmentResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceFragment.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
