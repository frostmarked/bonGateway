import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'cow',
        loadChildren: () => import('./cow/cow.module').then(m => m.CowModule),
      },
      {
        path: 'aboutus',
        loadChildren: () => import('./aboutus/aboutus.module').then(m => m.AboutusModule),
      },
    ]),
  ],
})
export class FarmRoutingModule {}
