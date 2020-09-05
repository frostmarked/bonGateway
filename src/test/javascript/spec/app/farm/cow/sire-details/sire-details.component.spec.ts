import { TestBed, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { of } from 'rxjs';

import { BonGatewayTestModule } from 'src/test/javascript/spec/test.module';
import { SireDetailsComponent } from 'app/farm/cow/sire-details/sire-details.component';
import {
  GetArticleGQL,
  GetCowGQL,
  FindCowPicturesGQL,
  PictureVo,
  PictureSourceVo,
  Visibility,
  ArticleVo,
  Category,
  SectionVo,
  CowVo,
} from 'app/bonpublicgraphql/bonpublicgraphql';
import { ActivatedRoute } from '@angular/router';
import { JhiTranslateDirective } from 'ng-jhipster';
import { ApolloModule } from 'apollo-angular';
import { BonVisibilityClassDirective } from 'app/shared/bon/bon-visibility-class.directive';
import { CowPictureDirective } from 'app/farm/cow/cow-pictures.directive';

describe('Component Tests', () => {
  describe('SireDetailsComponent', () => {
    let comp: SireDetailsComponent;
    let fixture: ComponentFixture<SireDetailsComponent>;
    let getArticleGQL: GetArticleGQL;
    let getCowGQL: GetCowGQL;
    let findCowPicturesGQL: FindCowPicturesGQL;

    const TEST_COW = {
      id: 100,
      earTagId: 200,
      name: 'cowname200',
      visibility: Visibility.RoleAnonymous,
      storyHandle: 'cattlestory200',
      matriId: 10,
      patriId: 20,
    } as CowVo;

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
    const API_PICTURES_RESPONSE$ = of({
      data: {
        apiPublicCowsPictures: [TEST_PICTURE],
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
        declarations: [SireDetailsComponent, JhiTranslateDirective, BonVisibilityClassDirective, CowPictureDirective],
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

      fixture = TestBed.createComponent(SireDetailsComponent);
      comp = fixture.componentInstance;
      getArticleGQL = fixture.debugElement.injector.get(GetArticleGQL);
      getCowGQL = fixture.debugElement.injector.get(GetCowGQL);
      findCowPicturesGQL = fixture.debugElement.injector.get(FindCowPicturesGQL);
    });

    it('should find sire on load', () => {
      // GIVEN
      spyOn(findCowPicturesGQL, 'fetch').and.returnValue(API_PICTURES_RESPONSE$);
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);
      spyOn(getCowGQL, 'fetch').and.returnValue(API_COWPARENT_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      const sire = comp.sire!;
      expect(sire.earTagId).toEqual(TEST_COW.earTagId);
      expect(sire.visibility).toEqual(TEST_COW.visibility);

      const liElements = fixture.debugElement.queryAll(By.css('.sire-item'));
      expect(liElements.length).toBe(1);
    });

    it('should find sire photos on load', done => {
      // GIVEN
      spyOn(findCowPicturesGQL, 'fetch').and.returnValue(API_PICTURES_RESPONSE$);
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);
      spyOn(getCowGQL, 'fetch').and.returnValue(API_COWPARENT_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      expect(findCowPicturesGQL.fetch).toHaveBeenCalled();
      comp.pictures$!.subscribe(pics => {
        expect(pics).not.toBeNull();
        expect(pics?.length).toEqual(1);
        const pic = pics ? pics[0] : null;
        expect(pic).not.toBeNull();
        expect(pic?.sources?.length).toEqual(1);
        const ps = pic?.sources ? pic?.sources[0] : null;
        expect(ps?.contentType).toEqual(TEST_PICTURE_SOURCE.contentType);
        done();
      });
    });

    it('should not find sire photos so use fallback image on load', done => {
      // GIVEN
      spyOn(findCowPicturesGQL, 'fetch').and.returnValue(
        of({
          data: {
            apiPublicCowsPictures: [],
          },
        })
      );
      spyOn(getArticleGQL, 'fetch').and.returnValue(API_ARTICLE_RESPONSE$);
      spyOn(getCowGQL, 'fetch').and.returnValue(API_COWPARENT_RESPONSE$);

      // WHEN
      comp.ngOnInit();
      fixture.detectChanges();

      // THEN
      expect(findCowPicturesGQL.fetch).toHaveBeenCalled();
      comp.pictures$!.subscribe(pics => {
        expect(pics).not.toBeNull();
        expect(pics?.length).toEqual(1);
        const pic = pics ? pics[0] : null;
        expect(pic).not.toBeNull();
        done();
      });
    });

    it('should find sire mother on load', done => {
      // GIVEN
      spyOn(findCowPicturesGQL, 'fetch').and.returnValue(API_PICTURES_RESPONSE$);
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

    it('should find sire father on load', done => {
      // GIVEN
      spyOn(findCowPicturesGQL, 'fetch').and.returnValue(API_PICTURES_RESPONSE$);
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
    let comp: SireDetailsComponent;
    let fixture: ComponentFixture<SireDetailsComponent>;
    let getArticleGQL: GetArticleGQL;
    let getCowGQL: GetCowGQL;
    let findCowPicturesGQL: FindCowPicturesGQL;

    const TEST_COW = {
      id: 100,
      earTagId: 200,
      name: 'cowname200',
      visibility: Visibility.RoleAnonymous,
      storyHandle: null,
      matriId: null,
      patriId: null,
    } as CowVo;

    const API_PICTURES_RESPONSE$ = of({
      data: {
        apiPublicCowsPictures: [],
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
        declarations: [SireDetailsComponent, JhiTranslateDirective, BonVisibilityClassDirective, CowPictureDirective],
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

      fixture = TestBed.createComponent(SireDetailsComponent);
      comp = fixture.componentInstance;
      getArticleGQL = fixture.debugElement.injector.get(GetArticleGQL);
      getCowGQL = fixture.debugElement.injector.get(GetCowGQL);
      findCowPicturesGQL = fixture.debugElement.injector.get(FindCowPicturesGQL);
    });

    it('should not query article on load', () => {
      // GIVEN
      spyOn(findCowPicturesGQL, 'fetch').and.returnValue(API_PICTURES_RESPONSE$);
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
      spyOn(findCowPicturesGQL, 'fetch').and.returnValue(API_PICTURES_RESPONSE$);
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
