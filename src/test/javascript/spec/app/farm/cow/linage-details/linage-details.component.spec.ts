import { TestBed, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { of } from 'rxjs';

import { BonGatewayTestModule } from 'src/test/javascript/spec/test.module';
import { LinageDetailsComponent } from 'app/farm/cow/linage-details/linage-details.component';
import {
  GetArticleGQL,
  FindCowsGQL,
  FindCowPhotosGQL,
  LinageVo,
  PhotographVo,
  Visibility,
  ArticleVo,
  Category,
  SectionVo,
} from 'app/bonpublicgraphql/bonpublicgraphql';
import { ActivatedRoute } from '@angular/router';
import { JhiTranslateDirective } from 'ng-jhipster';
import { ApolloModule } from 'apollo-angular';

describe('Component Tests', () => {
  describe('LinageDetailsComponent', () => {
    let comp: LinageDetailsComponent;
    let fixture: ComponentFixture<LinageDetailsComponent>;
    let getArticleGQL: GetArticleGQL;
    let findCowsGQL: FindCowsGQL;
    let findCowPhotosGQL: FindCowPhotosGQL;

    const beforeLinageTest = (route: ActivatedRoute) => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule, RouterTestingModule, ApolloModule],
        declarations: [LinageDetailsComponent, JhiTranslateDirective],
        schemas: [CUSTOM_ELEMENTS_SCHEMA],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: route,
          },
        ],
      }).compileComponents();

      fixture = TestBed.createComponent(LinageDetailsComponent);
      comp = fixture.componentInstance;
      getArticleGQL = fixture.debugElement.injector.get(GetArticleGQL);
      findCowsGQL = fixture.debugElement.injector.get(FindCowsGQL);
      findCowPhotosGQL = fixture.debugElement.injector.get(FindCowPhotosGQL);
    };

    it('should find lineage article and no cows on load', done => {
      const route: ActivatedRoute = ({
        data: of({ linageVo: { id: 123, familyname: 'abc', storyHandle: 'lineagehandle1' } as LinageVo }),
      } as any) as ActivatedRoute;
      beforeLinageTest(route);

      // GIVEN
      const TEST_ARTICLE = {
        id: 100,
        category: Category.Matrilineality,
        name: 'lineagehandle1',
        visibility: Visibility.RoleAnonymous,
        sections: [
          {
            id: 101,
            title: 'Muuu',
          } as SectionVo,
        ],
      } as ArticleVo;
      const API_ARTICLE_RESPONSE$ = of({
        data: {
          articleVO: TEST_ARTICLE,
        },
      });
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);

      // verify cow in another test case
      const API_COW_EMPTY_RESPONSE$ = of({
        data: {
          apiPublicCows: [],
        },
      });
      spyOn(findCowsGQL, 'fetch').and.returnValue(API_COW_EMPTY_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      expect(getArticleGQL.fetch).toHaveBeenCalled();
      comp.article$?.subscribe(article => {
        expect(article?.id).toEqual(TEST_ARTICLE.id);
        expect(article?.name).toEqual(TEST_ARTICLE.name);
        expect(article?.category).toEqual(TEST_ARTICLE.category);
        expect(article?.visibility).toEqual(TEST_ARTICLE.visibility);

        const liElements = fixture.debugElement.queryAll(By.css('.linage-section'));
        expect(liElements.length).toBe(1);

        done();
      });
    });

    it('should find dams and no article on load', done => {
      const route: ActivatedRoute = ({
        data: of({ linageVo: { id: 123, familyname: 'abc' } as LinageVo }),
      } as any) as ActivatedRoute;
      beforeLinageTest(route);

      // GIVEN
      const TEST_COW = {
        earTagId: 200,
        visibility: Visibility.RoleAnonymous,
      } as LinageVo;
      const API_COW_RESPONSE$ = of({
        data: {
          apiPublicCows: [TEST_COW],
        },
      });
      let imageCount = 0;
      spyOn(findCowsGQL, 'fetch').and.returnValue(API_COW_RESPONSE$);

      const TEST_PHOTO = {
        image: 'R0lGODlhAQABAIAAAMLCwgAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==',
        imageContentType: 'image/gif',
      } as PhotographVo;
      const API_PHOTOS_RESPONSE$ = of(
        {
          data: {
            apiPublicCowsPhotographs: [TEST_PHOTO],
          },
        },
        /* test missing photo */
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
      expect(findCowsGQL.fetch).toHaveBeenCalled();
      comp.dams$?.subscribe(dams => {
        expect(dams.length).toEqual(1);
        expect(dams[0].earTagId).toEqual(TEST_COW.earTagId);
        expect(dams[0].visibility).toEqual(TEST_COW.visibility);

        const liElements = fixture.debugElement.queryAll(By.css('.dam-item'));
        expect(liElements.length).toBe(1);

        expect(findCowPhotosGQL.fetch).toHaveBeenCalled();
        dams[0].photo$.subscribe(src => {
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
