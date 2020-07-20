import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { PastureUpdateComponent } from 'app/entities/bonLivestockService/pasture/pasture-update.component';
import { PastureService } from 'app/entities/bonLivestockService/pasture/pasture.service';
import { Pasture } from 'app/shared/model/bonLivestockService/pasture.model';

describe('Component Tests', () => {
  describe('Pasture Management Update Component', () => {
    let comp: PastureUpdateComponent;
    let fixture: ComponentFixture<PastureUpdateComponent>;
    let service: PastureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [PastureUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PastureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PastureUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PastureService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Pasture(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Pasture();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
