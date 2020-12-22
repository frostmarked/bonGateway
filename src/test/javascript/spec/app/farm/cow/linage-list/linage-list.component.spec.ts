import { TestBed, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { of } from 'rxjs';

import { BonGatewayTestModule } from 'src/test/javascript/spec/test.module';
import { LinageListComponent } from 'app/farm/cow/linage-list/linage-list.component';
import {
  FindLinagesGQL,
  FindCowPicturesGQL,
  LinageVo,
  PictureVo,
  PictureSourceVo,
  Visibility,
} from 'app/bonpublicgraphql/bonpublicgraphql';
import { ApolloModule } from 'apollo-angular';
import { BonVisibilityClassDirective } from 'app/shared/bon/bon-visibility-class.directive';
import { CowPictureDirective } from 'app/farm/cow/cow-pictures.directive';

describe('Component Tests', () => {
  describe('LinageListComponent', () => {
    let comp: LinageListComponent;
    let fixture: ComponentFixture<LinageListComponent>;
    let findLinagesGQL: FindLinagesGQL;
    let findCowPicturesGQL: FindCowPicturesGQL;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule, RouterTestingModule, ApolloModule],
        declarations: [LinageListComponent, BonVisibilityClassDirective, CowPictureDirective],
        schemas: [CUSTOM_ELEMENTS_SCHEMA],
      }).compileComponents();

      fixture = TestBed.createComponent(LinageListComponent);
      comp = fixture.componentInstance;
      findLinagesGQL = fixture.debugElement.injector.get(FindLinagesGQL);
      findCowPicturesGQL = fixture.debugElement.injector.get(FindCowPicturesGQL);
    });

    it('should find lineages and provide a default image on load', done => {
      // GIVEN
      const TEST_LINEAGE = {
        id: 123,
        familyname: 'abc',
      } as LinageVo;
      const API_LINEAGE_RESPONSE$ = of({
        data: {
          apiPublicLinages: [TEST_LINEAGE],
        },
      });
      let imageCount = 0;
      spyOn(findLinagesGQL, 'fetch').and.returnValue(API_LINEAGE_RESPONSE$);

      const TEST_PICTURE_SOURCE = {
        name: 'cow1_1.png',
        contentType: 'image/png',
        width: 192,
        height: 192,
        url: '/api/public/cows/200/pictures/1/cow1_1.png',
      } as PictureSourceVo;

      const TEST_PICTURE = {
        id: 1,
        taken: Date.now().toLocaleString(),
        visibility: Visibility.RoleAnonymous,
        caption: 'cap',
        sources: [TEST_PICTURE_SOURCE],
      } as PictureVo;
      const API_PICTURES_RESPONSE$ = of(
        {
          data: {
            apiPublicCowsPictures: [TEST_PICTURE],
          },
        } /* test missing photo */,
        {
          data: {
            apiPublicCowsPictures: [],
          },
        }
      );
      spyOn(findCowPicturesGQL, 'fetch').and.returnValue(API_PICTURES_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      expect(findLinagesGQL.fetch).toHaveBeenCalled();
      comp.linages$?.subscribe(lineages => {
        expect(lineages.length).toEqual(1);
        expect(lineages[0].id).toEqual(TEST_LINEAGE.id);
        expect(lineages[0].familyname).toEqual(TEST_LINEAGE.familyname);

        const liElements = fixture.debugElement.queryAll(By.css('.linage-item'));
        expect(liElements.length).toBe(1);

        expect(findCowPicturesGQL.fetch).toHaveBeenCalled();
        lineages[0].picture$.subscribe(picture => {
          const ps = picture?.sources ? picture?.sources[0] : null;
          if (imageCount === 0) {
            // startwith default
            expect(ps?.url).toContain('/content/images/');
            imageCount++;
          } else if (imageCount === 1) {
            // found image
            expect(ps?.url).toContain('/api/public/cows/');
            imageCount++;
          } else {
            // didnt find any image
            expect(ps?.url).toContain('https://picsum.photos');
            done();
          }
        });
      });
    });
  });
});
