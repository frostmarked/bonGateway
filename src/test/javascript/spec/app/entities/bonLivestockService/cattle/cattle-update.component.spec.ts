import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { CattleUpdateComponent } from 'app/entities/bonLivestockService/cattle/cattle-update.component';
import { CattleService } from 'app/entities/bonLivestockService/cattle/cattle.service';
import { Cattle } from 'app/shared/model/bonLivestockService/cattle.model';

describe('Component Tests', () => {
  describe('Cattle Management Update Component', () => {
    let comp: CattleUpdateComponent;
    let fixture: ComponentFixture<CattleUpdateComponent>;
    let service: CattleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [CattleUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CattleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CattleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CattleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Cattle(123);
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
        const entity = new Cattle();
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
