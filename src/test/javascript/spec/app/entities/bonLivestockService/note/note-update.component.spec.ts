import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { NoteUpdateComponent } from 'app/entities/bonLivestockService/note/note-update.component';
import { NoteService } from 'app/entities/bonLivestockService/note/note.service';
import { Note } from 'app/shared/model/bonLivestockService/note.model';

describe('Component Tests', () => {
  describe('Note Management Update Component', () => {
    let comp: NoteUpdateComponent;
    let fixture: ComponentFixture<NoteUpdateComponent>;
    let service: NoteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [NoteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NoteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NoteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NoteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Note(123);
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
        const entity = new Note();
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
