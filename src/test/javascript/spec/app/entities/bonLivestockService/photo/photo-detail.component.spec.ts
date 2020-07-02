import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { BonGatewayTestModule } from '../../../../test.module';
import { PhotoDetailComponent } from 'app/entities/bonLivestockService/photo/photo-detail.component';
import { Photo } from 'app/shared/model/bonLivestockService/photo.model';

describe('Component Tests', () => {
  describe('Photo Management Detail Component', () => {
    let comp: PhotoDetailComponent;
    let fixture: ComponentFixture<PhotoDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ photo: new Photo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [PhotoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PhotoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PhotoDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load photo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.photo).toEqual(jasmine.objectContaining({ id: 123 }));
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
