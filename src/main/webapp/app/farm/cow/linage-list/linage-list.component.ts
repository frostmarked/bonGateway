import { Component, OnInit } from '@angular/core';
import {
  FindLinagesGQL,
  FindCowPicturesGQL,
  PictureVo,
  Visibility,
  PictureSourceVo,
  LinageVo,
} from 'app/bonpublicgraphql/bonpublicgraphql';
import { map } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { CowService } from 'app/farm/cow/cow.service';

interface LinageItemVM {
  id: number;
  earTagId: number;
  familyname: string;
  visibility: string;
  polled: boolean;
  picture$: Observable<PictureVo>;
}

const LINEAGE_EARTAGID_IMAGEURL_MAP = new Map([
  [1, '/content/images/bon/lineage/1_europe/185_Europe-072003b.jpg'],
  [2, '/content/images/bon/lineage/2_eglantine/2095_Eglantine-092004a.jpg'],
  [3, '/content/images/bon/lineage/3_etincelle/176_Etincelle-072003b.jpg'],
  [6, '/content/images/bon/lineage/6_esther/2127_Polled_Minerve-072003a.jpg'],
  [7, '/content/images/bon/lineage/7_elyssee/75_Elysse-072004a.jpg'],
  [8, '/content/images/bon/lineage/8_eure/131_Eure-072003b.jpg'],
  [9, '/content/images/bon/lineage/9_emeraude/2099_Emeraude_ET-072003b.jpg'],
  [30, '/content/images/bon/lineage/30_delphine/69_Juliette-072003a.jpg'],
  [49, '/content/images/bon/lineage/49_epargne/154_Evita-082003b.jpg'],
  [52, '/content/images/bon/lineage/52_estafette/2089_Elle-062005a.jpg'],
  [98, '/content/images/bon/lineage/98_fanny/57_Fanny-082003a.jpg'],
  [988, '/content/images/bon/lineage/988_uppee/2091_Uppee_ET-082003a.jpg'],
]);

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
                picture$: this.getLineageImageUrl(linage!),
              } as LinageItemVM)
          )
        )
      );
  }

  private getLineageImageUrl(linage: LinageVo): Observable<PictureVo> {
    if (!LINEAGE_EARTAGID_IMAGEURL_MAP.has(linage.earTagId!)) {
      return this.cowService.getFirstCowPicture(linage.earTagId!);
    }
    const imgUrl = LINEAGE_EARTAGID_IMAGEURL_MAP.get(linage.earTagId!);
    const pvo = {
      id: 0,
      caption: linage.familyname,
      visibility: Visibility.RoleAnonymous,
      taken: new Date().toISOString(),
      sources: [
        {
          name: 'default-img',
          url: imgUrl,
          width: 400,
          height: 300,
          contentType: 'image/jpeg',
        } as PictureSourceVo,
      ],
    } as PictureVo;
    return of(pvo);
  }
}
