import { NgModule } from '@angular/core';
import { BonGatewaySharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { BonVisibilityClassDirective } from './bon/bon-visibility-class.directive';

@NgModule({
  imports: [BonGatewaySharedLibsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    BonVisibilityClassDirective,
  ],
  entryComponents: [LoginModalComponent],
  exports: [
    BonGatewaySharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    BonVisibilityClassDirective,
  ],
})
export class BonGatewaySharedModule {}
