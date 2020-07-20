import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { FragmentUpdateComponent } from 'app/entities/bonContentService/fragment/fragment-update.component';
import { FragmentService } from 'app/entities/bonContentService/fragment/fragment.service';
import { Fragment } from 'app/shared/model/bonContentService/fragment.model';

describe('Component Tests', () => {
  describe('Fragment Management Update Component', () => {
    let comp: FragmentUpdateComponent;
    let fixture: ComponentFixture<FragmentUpdateComponent>;
    let service: FragmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [FragmentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FragmentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FragmentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FragmentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Fragment(123);
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
        const entity = new Fragment();
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
