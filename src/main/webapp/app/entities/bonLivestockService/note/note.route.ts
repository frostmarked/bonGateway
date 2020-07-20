import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INote, Note } from 'app/shared/model/bonLivestockService/note.model';
import { NoteService } from './note.service';
import { NoteComponent } from './note.component';
import { NoteDetailComponent } from './note-detail.component';
import { NoteUpdateComponent } from './note-update.component';

@Injectable({ providedIn: 'root' })
export class NoteResolve implements Resolve<INote> {
  constructor(private service: NoteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INote> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((note: HttpResponse<Note>) => {
          if (note.body) {
            return of(note.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Note());
  }
}

export const noteRoute: Routes = [
  {
    path: '',
    component: NoteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'bonGatewayApp.bonLivestockServiceNote.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NoteDetailComponent,
    resolve: {
      note: NoteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServiceNote.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NoteUpdateComponent,
    resolve: {
      note: NoteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServiceNote.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NoteUpdateComponent,
    resolve: {
      note: NoteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonLivestockServiceNote.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
