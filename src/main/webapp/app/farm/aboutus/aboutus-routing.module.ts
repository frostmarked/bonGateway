import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompanyComponent } from './company/company.component';
import { ContactComponent } from './contact/contact.component';

const routes: Routes = [
  {
    path: 'company',
    component: CompanyComponent,
  },
  {
    path: 'contact',
    component: ContactComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AboutusRoutingModule {}
