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
  FindCowPicturesGQL,
  LinageVo,
  PictureVo,
  PictureSourceVo,
  Visibility,
  ArticleVo,
  Category,
  SectionVo,
} from 'app/bonpublicgraphql/bonpublicgraphql';
import { ActivatedRoute } from '@angular/router';
import { JhiTranslateDirective } from 'ng-jhipster';
import { ApolloModule } from 'apollo-angular';
import { BonVisibilityClassDirective } from 'app/shared/bon/bon-visibility-class.directive';
import { CowPictureDirective } from 'app/farm/cow/cow-pictures.directive';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';

describe('Component Tests', () => {
  describe('LinageDetailsComponent', () => {
    let comp: LinageDetailsComponent;
    let fixture: ComponentFixture<LinageDetailsComponent>;
    let getArticleGQL: GetArticleGQL;
    let findCowsGQL: FindCowsGQL;
    let findCowPicturesGQL: FindCowPicturesGQL;

    const beforeLinageTest = (route: ActivatedRoute) => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule, RouterTestingModule, ApolloModule, InfiniteScrollModule],
        declarations: [LinageDetailsComponent, JhiTranslateDirective, BonVisibilityClassDirective, CowPictureDirective],
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
      findCowPicturesGQL = fixture.debugElement.injector.get(FindCowPicturesGQL);
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
      const dams = comp.dams;
      expect(dams[0].earTagId).toEqual(TEST_COW.earTagId);
      expect(dams[0].visibility).toEqual(TEST_COW.visibility);

      expect(findCowPicturesGQL.fetch).toHaveBeenCalled();
      dams[0].picture$.subscribe(picture => {
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
