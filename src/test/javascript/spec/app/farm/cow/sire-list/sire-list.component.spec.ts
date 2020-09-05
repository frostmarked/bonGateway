import { TestBed, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { of } from 'rxjs';

import { BonGatewayTestModule } from 'src/test/javascript/spec/test.module';
import { SireListComponent } from 'app/farm/cow/sire-list/sire-list.component';
import { FindCowsGQL, FindCowPicturesGQL, CowVo, PictureVo, PictureSourceVo, Visibility } from 'app/bonpublicgraphql/bonpublicgraphql';
import { ApolloModule } from 'apollo-angular';
import { BonVisibilityClassDirective } from 'app/shared/bon/bon-visibility-class.directive';
import { CowPictureDirective } from 'app/farm/cow/cow-pictures.directive';

describe('Component Tests', () => {
  describe('SireListComponent', () => {
    let comp: SireListComponent;
    let fixture: ComponentFixture<SireListComponent>;
    let findCowsGQL: FindCowsGQL;
    let findCowPicturesGQL: FindCowPicturesGQL;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule, RouterTestingModule, ApolloModule],
        declarations: [SireListComponent, BonVisibilityClassDirective, CowPictureDirective],
        schemas: [CUSTOM_ELEMENTS_SCHEMA],
      }).compileComponents();

      fixture = TestBed.createComponent(SireListComponent);
      comp = fixture.componentInstance;
      findCowsGQL = fixture.debugElement.injector.get(FindCowsGQL);
      findCowPicturesGQL = fixture.debugElement.injector.get(FindCowPicturesGQL);
    });

    it('should find sires on load', done => {
      // GIVEN
      const TEST_COW = {
        earTagId: 200,
        visibility: Visibility.RoleAnonymous,
      } as CowVo;
      const API_COW_RESPONSE$ = of({
        data: {
          apiPublicCows: [TEST_COW],
        },
      });
      let imageCount = 0;
      spyOn(findCowsGQL, 'fetch').and.returnValue(API_COW_RESPONSE$);

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
        },
        /* test missing photo */
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
      expect(findCowsGQL.fetch).toHaveBeenCalled();
      comp.sires$?.subscribe(sires => {
        expect(sires.length).toEqual(1);
        expect(sires[0].earTagId).toEqual(TEST_COW.earTagId);
        expect(sires[0].visibility).toEqual(TEST_COW.visibility);

        const liElements = fixture.debugElement.queryAll(By.css('.sire-item'));
        expect(liElements.length).toBe(1);

        expect(findCowPicturesGQL.fetch).toHaveBeenCalled();
        sires[0].picture$.subscribe(picture => {
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
            expect(ps?.url).toContain('/content/images/');
            done();
          }
        });
      });
    });
  });
});
