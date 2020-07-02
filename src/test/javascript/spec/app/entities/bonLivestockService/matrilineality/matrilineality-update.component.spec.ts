import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { MatrilinealityUpdateComponent } from 'app/entities/bonLivestockService/matrilineality/matrilineality-update.component';
import { MatrilinealityService } from 'app/entities/bonLivestockService/matrilineality/matrilineality.service';
import { Matrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';

describe('Component Tests', () => {
  describe('Matrilineality Management Update Component', () => {
    let comp: MatrilinealityUpdateComponent;
    let fixture: ComponentFixture<MatrilinealityUpdateComponent>;
    let service: MatrilinealityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [MatrilinealityUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MatrilinealityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MatrilinealityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MatrilinealityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Matrilineality(123);
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
        const entity = new Matrilineality();
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
