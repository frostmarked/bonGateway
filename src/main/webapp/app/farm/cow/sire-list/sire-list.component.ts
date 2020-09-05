import { Component, OnInit } from '@angular/core';
import { FindCowPicturesGQL, FindCowsGQL, PictureVo, GenderEquals } from 'app/bonpublicgraphql/bonpublicgraphql';
import { DEFAULT_PICTURE } from 'app/shared/bon/picturevo-util';
import { map, startWith, finalize } from 'rxjs/operators';
import { Observable, BehaviorSubject } from 'rxjs';

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
  loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  sires$?: Observable<Array<SireItemVM>>;

  constructor(private findCowsGQL: FindCowsGQL, private findCowPicturesGQL: FindCowPicturesGQL) {}

  ngOnInit(): void {
    this.sires$ = this.getSires();
  }

  private getSires(): Observable<Array<SireItemVM>> {
    setTimeout(() => this.loadingSubject.next(true));
    return this.findCowsGQL.fetch({ genderEquals: GenderEquals.Bull, size: 100, sort: ['earTagId,desc'] }).pipe(
      map(result => result.data.apiPublicCows),
      map(cows =>
        cows!.map(
          cow =>
            ({
              earTagId: cow!.earTagId,
              name: cow!.name,
              visibility: cow!.visibility,
              picture$: this.getCowPicture(cow!.earTagId!),
            } as SireItemVM)
        )
      ),
      finalize(() => this.loadingSubject.next(false))
    );
  }

  private getCowPicture(earTagId: number): Observable<PictureVo> {
    return this.findCowPicturesGQL.fetch({ earTagId, size: 1 }).pipe(
      map(result => result.data.apiPublicCowsPictures),
      map(pics => (pics && pics[0] ? pics[0] : DEFAULT_PICTURE)),
      startWith(DEFAULT_PICTURE)
    );
  }
}
