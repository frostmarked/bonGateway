import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { BonGatewayTestModule } from '../../../../test.module';
import { LocalizedDetailComponent } from 'app/entities/bonContentService/localized/localized-detail.component';
import { Localized } from 'app/shared/model/bonContentService/localized.model';

describe('Component Tests', () => {
  describe('Localized Management Detail Component', () => {
    let comp: LocalizedDetailComponent;
    let fixture: ComponentFixture<LocalizedDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ localized: new Localized(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [LocalizedDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LocalizedDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LocalizedDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load localized on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.localized).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
