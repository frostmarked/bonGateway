import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';

import { Apollo } from 'apollo-angular';
import gql from 'graphql-tag';
// import { FindArticles, FindArticlesVariables } from 'app/bonpublicgraphql/bonpublicgraphql-types';

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

  constructor(private accountService: AccountService, private loginModalService: LoginModalService, private apollo: Apollo) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));

    this.apollo
      .watchQuery({
        query: gql`
          {
            apiPublicTags {
              id
              name
            }
          }
        `,
      })
      .valueChanges.subscribe(result => {
        this.data = result.data && result.data;
        this.msg = JSON.stringify(this.data);
        this.loading = result.loading;
        this.errors = result.errors;
      });
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
