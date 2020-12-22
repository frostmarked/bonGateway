import { Component, OnInit } from '@angular/core';
import { FindCowPicturesGQL, FindCowsGQL, PictureVo, GenderEquals } from 'app/bonpublicgraphql/bonpublicgraphql';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { CowService } from 'app/farm/cow/cow.service';

interface SireItemVM {
  id: number;
  earTagId: number;
  name: string;
  visibility: string;
  polled: boolean;
  picture$: Observable<PictureVo>;
}

@Component({
  selector: 'jhi-sire-list',
  templateUrl: './sire-list.component.html',
  styleUrls: ['./sire-list.component.scss'],
})
export class SireListComponent implements OnInit {
  sires$?: Observable<Array<SireItemVM>>;

  constructor(private findCowsGQL: FindCowsGQL, private findCowPicturesGQL: FindCowPicturesGQL, private cowService: CowService) {}

  ngOnInit(): void {
    this.sires$ = this.getSires();
  }

  private getSires(): Observable<Array<SireItemVM>> {
    return this.findCowsGQL.fetch({ genderEquals: GenderEquals.Bull, size: 100, sort: ['earTagId,desc'] }).pipe(
      map(result => result.data.apiPublicCows),
      map(cows =>
        cows!.map(
          cow =>
            ({
              earTagId: cow!.earTagId,
              name: cow!.name,
              visibility: cow!.visibility,
              picture$: this.cowService.getFirstCowPicture(cow!.earTagId!),
            } as SireItemVM)
        )
      )
    );
  }
}
