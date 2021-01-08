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
  sires: Array<SireItemVM> = [];
  page = -1;

  constructor(private findCowsGQL: FindCowsGQL, private findCowPicturesGQL: FindCowPicturesGQL, private cowService: CowService) {}

  ngOnInit(): void {
    this.onScroll();
  }

  onScroll(): void {
    this.getSires(++this.page).subscribe(arr => this.sires.push(...arr));
  }

  private getSires(pageNumber: number): Observable<Array<SireItemVM>> {
    return this.findCowsGQL.fetch({ genderEquals: GenderEquals.Bull, page: pageNumber, sort: ['earTagId,desc'] }).pipe(
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
