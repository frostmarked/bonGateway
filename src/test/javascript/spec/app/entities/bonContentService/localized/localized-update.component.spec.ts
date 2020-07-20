import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { LocalizedUpdateComponent } from 'app/entities/bonContentService/localized/localized-update.component';
import { LocalizedService } from 'app/entities/bonContentService/localized/localized.service';
import { Localized } from 'app/shared/model/bonContentService/localized.model';

describe('Component Tests', () => {
  describe('Localized Management Update Component', () => {
    let comp: LocalizedUpdateComponent;
    let fixture: ComponentFixture<LocalizedUpdateComponent>;
    let service: LocalizedService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [LocalizedUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(LocalizedUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LocalizedUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LocalizedService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Localized(123);
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
        const entity = new Localized();
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
