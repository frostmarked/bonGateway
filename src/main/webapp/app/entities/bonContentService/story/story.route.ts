import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStory, Story } from 'app/shared/model/bonContentService/story.model';
import { StoryService } from './story.service';
import { StoryComponent } from './story.component';
import { StoryDetailComponent } from './story-detail.component';
import { StoryUpdateComponent } from './story-update.component';

@Injectable({ providedIn: 'root' })
export class StoryResolve implements Resolve<IStory> {
  constructor(private service: StoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((story: HttpResponse<Story>) => {
          if (story.body) {
            return of(story.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Story());
  }
}

export const storyRoute: Routes = [
  {
    path: '',
    component: StoryComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceStory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StoryDetailComponent,
    resolve: {
      story: StoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceStory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StoryUpdateComponent,
    resolve: {
      story: StoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceStory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StoryUpdateComponent,
    resolve: {
      story: StoryResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'bonGatewayApp.bonContentServiceStory.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
