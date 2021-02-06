import { NgModule } from '@angular/core';
import { BonGatewaySharedModule } from 'app/shared/shared.module';

import { AboutusRoutingModule } from './aboutus-routing.module';
import { CompanyComponent } from './company/company.component';
import { ContactComponent } from './contact/contact.component';

@NgModule({
  declarations: [CompanyComponent, ContactComponent],
  imports: [BonGatewaySharedModule, AboutusRoutingModule],
})
export class AboutusModule {}
