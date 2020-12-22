import { Component, OnInit } from '@angular/core';
import { FindLinagesGQL, FindCowPicturesGQL, PictureVo } from 'app/bonpublicgraphql/bonpublicgraphql';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { CowService } from 'app/farm/cow/cow.service';

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
  linages$?: Observable<Array<LinageItemVM>>;

  constructor(private findLinagesGQL: FindLinagesGQL, private findCowPicturesGQL: FindCowPicturesGQL, private cowService: CowService) {}

  ngOnInit(): void {
    this.linages$ = this.getLinages();
  }

  private getLinages(): Observable<Array<LinageItemVM>> {
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
                picture$: this.cowService.getFirstCowPicture(linage!.earTagId!),
              } as LinageItemVM)
          )
        )
      );
  }
}
