import { Component, OnInit } from '@angular/core';
import { FindLinagesGQL, FindCowPicturesGQL, PictureVo } from 'app/bonpublicgraphql/bonpublicgraphql';
import { map, startWith, finalize } from 'rxjs/operators';
import { Observable, BehaviorSubject } from 'rxjs';
import { DEFAULT_PICTURE, randomPictureVoFromPicsum } from 'app/shared/bon/picturevo-util';

interface LinageItemVM {
  id: number;
  earTagId: number;
  familyname: string;
  visibility: string;
  polled: boolean;
  picture$: Observable<PictureVo>;
}

@Component({
  selector: 'jhi-linage-list',
  templateUrl: './linage-list.component.html',
  styleUrls: ['./linage-list.component.scss'],
})
export class LinageListComponent implements OnInit {
  loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  linages$?: Observable<Array<LinageItemVM>>;

  constructor(private findLinagesGQL: FindLinagesGQL, private findCowPicturesGQL: FindCowPicturesGQL) {}

  ngOnInit(): void {
    this.linages$ = this.getLinages();
  }

  private getLinages(): Observable<Array<LinageItemVM>> {
    setTimeout(() => this.loadingSubject.next(true));
    return this.findLinagesGQL
      .fetch({ size: 100 }) // unlikly that there will be more then 20
      .pipe(
        map(result => result.data.apiPublicLinages),
        map(linages =>
          linages!.map(
            linage =>
              ({
                id: linage!.id,
                earTagId: linage!.earTagId,
                familyname: linage!.familyname,
                visibility: linage!.visibility,
                polled: linage!.polled,
                picture$: this.getLinagePicture(linage!.earTagId!),
              } as LinageItemVM)
          )
        ),
        finalize(() => this.loadingSubject.next(false))
      );
  }

  private getLinagePicture(earTagId: number): Observable<PictureVo> {
    return this.findCowPicturesGQL.fetch({ earTagId, size: 1 }).pipe(
      map(result => result.data.apiPublicCowsPictures),
      map(pics => (pics && pics[0] ? pics[0] : randomPictureVoFromPicsum('seed' + earTagId))),
      startWith(DEFAULT_PICTURE)
    );
  }
}
