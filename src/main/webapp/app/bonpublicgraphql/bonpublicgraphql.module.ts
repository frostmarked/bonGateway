import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { isDevMode } from '@angular/core';
import { ApolloModule, APOLLO_OPTIONS } from 'apollo-angular';
import { HttpLinkModule, HttpLink } from 'apollo-angular-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { ApolloLink } from 'apollo-link';
import { onError } from 'apollo-link-error';
import { setContext } from 'apollo-link-context';

import { DEBUG_INFO_ENABLED } from 'app/app.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { AuthServerProvider } from 'app/core/auth/auth-jwt.service';
import { DefaultOptions } from 'apollo-client';
import { JhiEventManager, JhiEventWithContent } from 'ng-jhipster';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { BonGatewaySharedModule } from 'app/shared/shared.module';

const serverUrl = SERVER_API_URL || location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');
const uri = `${serverUrl}/graphql`;

export function provideApollo(httpLink: HttpLink, authServerProvider: AuthServerProvider, eventManager: JhiEventManager): any {
  const basic = setContext(() => ({
    headers: {
      Accept: 'charset=utf-8',
    },
  }));

  const token = authServerProvider.getToken();
  const auth = setContext(() => ({
    headers: {
      Authorization: `Bearer ${token}`,
    },
  }));

  const defaultOptions: DefaultOptions = {
    watchQuery: {
      fetchPolicy: 'no-cache',
      errorPolicy: 'all',
    },
    query: {
      fetchPolicy: 'no-cache',
      errorPolicy: 'all',
    },
  };

  const errorLink = onError(({ graphQLErrors, networkError }) => {
    if (graphQLErrors) {
      graphQLErrors.map(({ message, locations, path }) => {
        if (DEBUG_INFO_ENABLED) console.error(`[GraphQL error]: Message: ${message}, Location: ${locations}, Path: ${path}`);
        eventManager.broadcast(
          // TODO e.g. add key: "graphql.query", params: [locations, path]
          new JhiEventWithContent<AlertError>('bonGatewayApp.error', { message })
        );
      });
    }

    if (networkError) {
      if (DEBUG_INFO_ENABLED) console.error(`[Network error]: ${networkError}`);
      eventManager.broadcast(
        // TODO e.g. add key: "graphql.network", params: networkError
        new JhiEventWithContent<AlertError>('bonGatewayApp.error', { message: `[Network error]: ${networkError}` })
      );
    }
  });

  const link = ApolloLink.from([basic, auth, errorLink, httpLink.create({ uri })]);
  const cache = new InMemoryCache();

  return isDevMode() ? { link, cache, defaultOptions } : { link, cache };
}

@NgModule({
  exports: [HttpClientModule, ApolloModule, HttpLinkModule],
  providers: [
    {
      provide: APOLLO_OPTIONS,
      useFactory: provideApollo,
      deps: [HttpLink, AuthServerProvider, JhiEventManager],
    },
  ],
  imports: [BonGatewaySharedModule],
})
export class BonPublicGraphQLModule {}
