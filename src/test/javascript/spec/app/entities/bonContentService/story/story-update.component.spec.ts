import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { StoryUpdateComponent } from 'app/entities/bonContentService/story/story-update.component';
import { StoryService } from 'app/entities/bonContentService/story/story.service';
import { Story } from 'app/shared/model/bonContentService/story.model';

describe('Component Tests', () => {
  describe('Story Management Update Component', () => {
    let comp: StoryUpdateComponent;
    let fixture: ComponentFixture<StoryUpdateComponent>;
    let service: StoryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [StoryUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(StoryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StoryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StoryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Story(123);
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
        const entity = new Story();
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
