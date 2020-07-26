import { TestBed, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { of } from 'rxjs';

import { BonGatewayTestModule } from 'src/test/javascript/spec/test.module';
import { LinageListComponent } from 'app/farm/cow/linage-list/linage-list.component';
import { FindLinagesGQL, FindCowPhotosGQL, LinageVo, PhotographVo } from 'app/bonpublicgraphql/bonpublicgraphql';
import { ApolloModule } from 'apollo-angular';

describe('Component Tests', () => {
  describe('LinageListComponent', () => {
    let comp: LinageListComponent;
    let fixture: ComponentFixture<LinageListComponent>;
    let findLinagesGQL: FindLinagesGQL;
    let findCowPhotosGQL: FindCowPhotosGQL;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule, RouterTestingModule, ApolloModule],
        declarations: [LinageListComponent],
        schemas: [CUSTOM_ELEMENTS_SCHEMA],
      }).compileComponents();

      fixture = TestBed.createComponent(LinageListComponent);
      comp = fixture.componentInstance;
      findLinagesGQL = fixture.debugElement.injector.get(FindLinagesGQL);
      findCowPhotosGQL = fixture.debugElement.injector.get(FindCowPhotosGQL);
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

      const TEST_PHOTO = {
        image: 'R0lGODlhAQABAIAAAMLCwgAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==',
        imageContentType: 'image/gif',
      } as PhotographVo;
      const API_PHOTOS_RESPONSE$ = of(
        {
          data: {
            apiPublicCowsPhotographs: [TEST_PHOTO],
          },
        } /* test missing photo */,
        {
          data: {
            apiPublicCowsPhotographs: [],
          },
        }
      );
      spyOn(findCowPhotosGQL, 'fetch').and.returnValue(API_PHOTOS_RESPONSE$);

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

        expect(findCowPhotosGQL.fetch).toHaveBeenCalled();
        lineages[0].photo$.subscribe(src => {
          if (imageCount === 0) {
            // startwith default
            expect(src).toContain('/content/images/');
            imageCount++;
          } else if (imageCount === 1) {
            // found image
            expect(src).toContain('base64');
            imageCount++;
          } else {
            // didnt find any image
            expect(src).toContain('/content/images/');
            done();
          }
        });
      });
    });
  });
});
