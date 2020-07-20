import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'matrilineality',
        loadChildren: () =>
          import('./bonLivestockService/matrilineality/matrilineality.module').then(m => m.BonLivestockServiceMatrilinealityModule),
      },
      {
        path: 'cattle',
        loadChildren: () => import('./bonLivestockService/cattle/cattle.module').then(m => m.BonLivestockServiceCattleModule),
      },
      {
        path: 'note',
        loadChildren: () => import('./bonLivestockService/note/note.module').then(m => m.BonLivestockServiceNoteModule),
      },
      {
        path: 'pasture',
        loadChildren: () => import('./bonLivestockService/pasture/pasture.module').then(m => m.BonLivestockServicePastureModule),
      },
      {
        path: 'photo',
        loadChildren: () => import('./bonLivestockService/photo/photo.module').then(m => m.BonLivestockServicePhotoModule),
      },
      {
        path: 'source-file',
        loadChildren: () => import('./bonReplicaService/source-file/source-file.module').then(m => m.BonReplicaServiceSourceFileModule),
      },
      {
        path: 'bovine',
        loadChildren: () => import('./bonReplicaService/bovine/bovine.module').then(m => m.BonReplicaServiceBovineModule),
      },
      {
        path: 'blup',
        loadChildren: () => import('./bonReplicaService/blup/blup.module').then(m => m.BonReplicaServiceBlupModule),
      },
      {
        path: 'journal-entry',
        loadChildren: () =>
          import('./bonReplicaService/journal-entry/journal-entry.module').then(m => m.BonReplicaServiceJournalEntryModule),
      },
      {
        path: 'story',
        loadChildren: () => import('./bonContentService/story/story.module').then(m => m.BonContentServiceStoryModule),
      },
      {
        path: 'fragment',
        loadChildren: () => import('./bonContentService/fragment/fragment.module').then(m => m.BonContentServiceFragmentModule),
      },
      {
        path: 'localized',
        loadChildren: () => import('./bonContentService/localized/localized.module').then(m => m.BonContentServiceLocalizedModule),
      },
      {
        path: 'tag',
        loadChildren: () => import('./bonContentService/tag/tag.module').then(m => m.BonContentServiceTagModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BonGatewayEntityModule {}
