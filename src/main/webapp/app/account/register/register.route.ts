import { Route } from '@angular/router';

import { RegisterComponent } from './register.component';
import { PUBLIC_ACCOUNT_REGISTRATION } from 'app/app.constants';
import { Authority } from 'app/shared/constants/authority.constants';

export const registerRoute: Route = {
  path: 'register',
  component: RegisterComponent,
  data: {
    authorities: PUBLIC_ACCOUNT_REGISTRATION ? [] : [Authority.ADMIN],
    pageTitle: 'register.title',
  },
};
