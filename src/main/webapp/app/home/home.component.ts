import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';

import { Apollo } from 'apollo-angular';
import gql from 'graphql-tag';
import { I18n, FindArticlesGQL, FindTagsQuery } from 'app/bonpublicgraphql/bonpublicgraphql';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  data?: any;
  msg?: String;
  loading = true;
  errors: any;

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private apollo: Apollo,
    private findArticlesGQL: FindArticlesGQL
  ) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));

    //  this.fetchTags();
    this.fetchArticles();
  }

  fetchTags(): void {
    const customFindTagsQuery = gql`
      query aFewTags($size: Int) {
        apiPublicTags(size: $size) {
          id
          name
        }
      }
    `;
    this.apollo
      .watchQuery<FindTagsQuery>({
        query: customFindTagsQuery,
        variables: {
          size: 3,
        },
      })
      .valueChanges.subscribe(result => {
        this.data = result.data && result.data;
        this.msg = JSON.stringify(this.data);
        this.errors = result.errors;
        this.loading = result.loading;
      });
  }

  fetchArticles(): void {
    this.findArticlesGQL
      .fetch({ i18n: I18n.Sv })
      .pipe(
        map(result => result.data.apiPublicArticles),
        catchError(err => throwError(`Caught error: ${err}`))
      )
      .subscribe(
        arr => (this.msg = JSON.stringify(arr)),
        err => (this.errors = err),
        () => (this.loading = false)
      );
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
}
