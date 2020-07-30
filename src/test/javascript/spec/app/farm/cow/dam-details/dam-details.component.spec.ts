import { TestBed, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { of } from 'rxjs';

import { BonGatewayTestModule } from 'src/test/javascript/spec/test.module';
import { DamDetailsComponent } from 'app/farm/cow/dam-details/dam-details.component';
import {
  GetArticleGQL,
  GetCowGQL,
  FindCowPhotosGQL,
  PhotographVo,
  Visibility,
  ArticleVo,
  Category,
  SectionVo,
  CowVo,
} from 'app/bonpublicgraphql/bonpublicgraphql';
import { ActivatedRoute } from '@angular/router';
import { JhiTranslateDirective } from 'ng-jhipster';
import { ApolloModule } from 'apollo-angular';

describe('Component Tests', () => {
  describe('DamDetailsComponent', () => {
    let comp: DamDetailsComponent;
    let fixture: ComponentFixture<DamDetailsComponent>;
    let getArticleGQL: GetArticleGQL;
    let getCowGQL: GetCowGQL;
    let findCowPhotosGQL: FindCowPhotosGQL;

    const TEST_COW = {
      id: 100,
      earTagId: 200,
      name: 'cowname200',
      visibility: Visibility.RoleAnonymous,
      storyHandle: 'cattlestory200',
      matriId: 10,
      patriId: 20,
    } as CowVo;

    const TEST_PHOTO = {
      image: 'R0lGODlhAQABAIAAAMLCwgAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==',
      imageContentType: 'image/gif',
    } as PhotographVo;
    const API_PHOTOS_RESPONSE$ = of({
      data: {
        apiPublicCowsPhotographs: [TEST_PHOTO],
      },
    });

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

    const TEST_COW_PARENT = {
      id: 10,
      earTagId: 100,
      name: 'cowparentname200',
      visibility: Visibility.RoleAnonymous,
    } as CowVo;
    const API_COWPARENT_RESPONSE$ = of({
      data: {
        cowVO: TEST_COW_PARENT,
      },
    });

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule, RouterTestingModule, ApolloModule],
        declarations: [DamDetailsComponent, JhiTranslateDirective],
        schemas: [CUSTOM_ELEMENTS_SCHEMA],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: ({
              data: of({ cowVo: TEST_COW }),
            } as any) as ActivatedRoute,
          },
        ],
      }).compileComponents();

      fixture = TestBed.createComponent(DamDetailsComponent);
      comp = fixture.componentInstance;
      getArticleGQL = fixture.debugElement.injector.get(GetArticleGQL);
      getCowGQL = fixture.debugElement.injector.get(GetCowGQL);
      findCowPhotosGQL = fixture.debugElement.injector.get(FindCowPhotosGQL);
    });

    it('should find dam on load', () => {
      // GIVEN
      spyOn(findCowPhotosGQL, 'fetch').and.returnValue(API_PHOTOS_RESPONSE$);
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);
      spyOn(getCowGQL, 'fetch').and.returnValue(API_COWPARENT_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      const dam = comp.dam!;
      expect(dam.earTagId).toEqual(TEST_COW.earTagId);
      expect(dam.visibility).toEqual(TEST_COW.visibility);

      const liElements = fixture.debugElement.queryAll(By.css('.dam-item'));
      expect(liElements.length).toBe(1);
    });

    it('should find dam photos on load', done => {
      // GIVEN
      spyOn(findCowPhotosGQL, 'fetch').and.returnValue(API_PHOTOS_RESPONSE$);
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);
      spyOn(getCowGQL, 'fetch').and.returnValue(API_COWPARENT_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      expect(findCowPhotosGQL.fetch).toHaveBeenCalled();
      comp.photos$!.subscribe(photos => {
        expect(photos).not.toBeNull();
        expect(photos?.length).toEqual(1);
        const photo = photos ? photos[0] : null;
        expect(photo).not.toBeNull();
        expect(photo?.imageContentType).toEqual(TEST_PHOTO.imageContentType);
        done();
      });
    });

    it('should not find dam photos so use fallback image on load', done => {
      // GIVEN
      spyOn(findCowPhotosGQL, 'fetch').and.returnValue(
        of({
          data: {
            apiPublicCowsPhotographs: [],
          },
        })
      );
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);
      spyOn(getCowGQL, 'fetch').and.returnValue(API_COWPARENT_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      expect(findCowPhotosGQL.fetch).toHaveBeenCalled();
      comp.photos$!.subscribe(photos => {
        expect(photos).not.toBeNull();
        expect(photos?.length).toEqual(1);
        const photo = photos ? photos[0] : null;
        expect(photo).not.toBeNull();
        done();
      });
    });

    it('should find dam mother on load', done => {
      // GIVEN
      spyOn(findCowPhotosGQL, 'fetch').and.returnValue(API_PHOTOS_RESPONSE$);
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);
      spyOn(getCowGQL, 'fetch').and.returnValue(API_COWPARENT_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      expect(getCowGQL.fetch).toHaveBeenCalled();
      comp.matri$!.subscribe(matri => {
        expect(matri?.name).toEqual(TEST_COW_PARENT.name);
        done();
      });
    });

    it('should find dam father on load', done => {
      // GIVEN
      spyOn(findCowPhotosGQL, 'fetch').and.returnValue(API_PHOTOS_RESPONSE$);
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);
      spyOn(getCowGQL, 'fetch').and.returnValue(API_COWPARENT_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      expect(getCowGQL.fetch).toHaveBeenCalled();
      comp.patri$!.subscribe(patri => {
        expect(patri?.name).toEqual(TEST_COW_PARENT.name);
        done();
      });
    });
  });

  describe('DamDetailsComponent with less data', () => {
    let comp: DamDetailsComponent;
    let fixture: ComponentFixture<DamDetailsComponent>;
    let getArticleGQL: GetArticleGQL;
    let getCowGQL: GetCowGQL;
    let findCowPhotosGQL: FindCowPhotosGQL;

    const TEST_COW = {
      id: 100,
      earTagId: 200,
      name: 'cowname200',
      visibility: Visibility.RoleAnonymous,
      storyHandle: null,
      matriId: null,
      patriId: null,
    } as CowVo;

    const API_PHOTOS_RESPONSE$ = of({
      data: {
        apiPublicCowsPhotographs: [],
      },
    });

    const API_ARTICLE_RESPONSE$ = of({
      data: {
        articleVO: null,
      },
    });

    const API_COWPARENT_RESPONSE$ = of({
      data: {
        cowVO: null,
      },
    });

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule, RouterTestingModule, ApolloModule],
        declarations: [DamDetailsComponent, JhiTranslateDirective],
        schemas: [CUSTOM_ELEMENTS_SCHEMA],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: ({
              data: of({ cowVo: TEST_COW }),
            } as any) as ActivatedRoute,
          },
        ],
      }).compileComponents();

      fixture = TestBed.createComponent(DamDetailsComponent);
      comp = fixture.componentInstance;
      getArticleGQL = fixture.debugElement.injector.get(GetArticleGQL);
      getCowGQL = fixture.debugElement.injector.get(GetCowGQL);
      findCowPhotosGQL = fixture.debugElement.injector.get(FindCowPhotosGQL);
    });

    it('should not query article on load', () => {
      // GIVEN
      spyOn(findCowPhotosGQL, 'fetch').and.returnValue(API_PHOTOS_RESPONSE$);
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);
      spyOn(getCowGQL, 'fetch').and.returnValue(API_COWPARENT_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      expect(getCowGQL.fetch).not.toHaveBeenCalled();
    });

    it('should not query mother or father on load', () => {
      // GIVEN
      spyOn(findCowPhotosGQL, 'fetch').and.returnValue(API_PHOTOS_RESPONSE$);
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);
      spyOn(getCowGQL, 'fetch').and.returnValue(API_COWPARENT_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      expect(getCowGQL.fetch).not.toHaveBeenCalled();
    });
  });
});
