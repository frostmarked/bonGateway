import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IJournalEntry, JournalEntry } from 'app/shared/model/bonReplicaService/journal-entry.model';
import { JournalEntryService } from './journal-entry.service';
import { JournalEntryComponent } from './journal-entry.component';
import { JournalEntryDetailComponent } from './journal-entry-detail.component';

@Injectable({ providedIn: 'root' })
export class JournalEntryResolve implements Resolve<IJournalEntry> {
  constructor(private service: JournalEntryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IJournalEntry> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((journalEntry: HttpResponse<JournalEntry>) => {
          if (journalEntry.body) {
            return of(journalEntry.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new JournalEntry());
  }
}

export const journalEntryRoute: Routes = [
  {
    path: '',
    component: JournalEntryComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'bonGatewayApp.bonReplicaServiceJournalEntry.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: JournalEntryDetailComponent,
    resolve: {
      journalEntry: JournalEntryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonReplicaServiceJournalEntry.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
