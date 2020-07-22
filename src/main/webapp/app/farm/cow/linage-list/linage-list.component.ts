import { Component, OnInit } from '@angular/core';
import { FindLinagesGQL, FindCowPhotosGQL } from 'app/bonpublicgraphql/bonpublicgraphql';
import { map, startWith, finalize } from 'rxjs/operators';
import { Observable } from 'rxjs';

interface LinageItemVM {
  id: number;
  earTagId: number;
  familyname: string;
  visibility: string;
  polled: boolean;
  photo$: Observable<string>;
}

const DEFAULT_IMG = '/content/images/bon/simple-cow-logo-limousin.png';

@Component({
  selector: 'jhi-linage-list',
  templateUrl: './linage-list.component.html',
  styleUrls: ['./linage-list.component.scss'],
})
export class LinageListComponent implements OnInit {
  loading = true;
  linages$?: Observable<Array<LinageItemVM>>;

  constructor(private findLinagesGQL: FindLinagesGQL, private findCowPhotosGQL: FindCowPhotosGQL) {}

  ngOnInit(): void {
    this.linages$ = this.getLinages();
  }

  private getLinages(): Observable<Array<LinageItemVM>> {
    this.loading = true;
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
                photo$: this.getLinagePhoto(linage!.earTagId!),
              } as LinageItemVM)
          )
        ),
        finalize(() => (this.loading = false))
      );
  }

  // use default image until actual photo is fetched
  // or return default if none exists
  private getLinagePhoto(earTagId: number): Observable<string> {
    return this.findCowPhotosGQL.fetch({ earTagId, size: 1 }).pipe(
      map(result => result.data.apiPublicCowPhotographs),
      map(photos => {
        if (photos && photos[0]) {
          return `data:${photos[0].imageContentType};base64,${photos[0].image}`;
        } else {
          return DEFAULT_IMG;
        }
      }),
      startWith(DEFAULT_IMG)
    );
  }
}
