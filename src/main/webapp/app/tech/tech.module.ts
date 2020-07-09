import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TechRoutingModule } from './tech-routing.module';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [HomeComponent],
  imports: [CommonModule, TechRoutingModule],
})
export class TechModule {}
