import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISourceFile, SourceFile } from 'app/shared/model/bonReplicaService/source-file.model';
import { SourceFileService } from './source-file.service';
import { SourceFileComponent } from './source-file.component';
import { SourceFileDetailComponent } from './source-file-detail.component';
import { SourceFileUpdateComponent } from './source-file-update.component';

@Injectable({ providedIn: 'root' })
export class SourceFileResolve implements Resolve<ISourceFile> {
  constructor(private service: SourceFileService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISourceFile> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sourceFile: HttpResponse<SourceFile>) => {
          if (sourceFile.body) {
            return of(sourceFile.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SourceFile());
  }
}

export const sourceFileRoute: Routes = [
  {
    path: '',
    component: SourceFileComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'bonGatewayApp.bonReplicaServiceSourceFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SourceFileDetailComponent,
    resolve: {
      sourceFile: SourceFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonReplicaServiceSourceFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SourceFileUpdateComponent,
    resolve: {
      sourceFile: SourceFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonReplicaServiceSourceFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SourceFileUpdateComponent,
    resolve: {
      sourceFile: SourceFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonReplicaServiceSourceFile.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
