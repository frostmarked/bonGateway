import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AboutusRoutingModule } from './aboutus-routing.module';
import { CompanyComponent } from './company/company.component';
import { ContactComponent } from './contact/contact.component';

@NgModule({
  declarations: [CompanyComponent, ContactComponent],
  imports: [CommonModule, AboutusRoutingModule],
})
export class AboutusModule {}
