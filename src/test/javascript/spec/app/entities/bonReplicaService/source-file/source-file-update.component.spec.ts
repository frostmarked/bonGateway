import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { SourceFileUpdateComponent } from 'app/entities/bonReplicaService/source-file/source-file-update.component';
import { SourceFileService } from 'app/entities/bonReplicaService/source-file/source-file.service';
import { SourceFile } from 'app/shared/model/bonReplicaService/source-file.model';

describe('Component Tests', () => {
  describe('SourceFile Management Update Component', () => {
    let comp: SourceFileUpdateComponent;
    let fixture: ComponentFixture<SourceFileUpdateComponent>;
    let service: SourceFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [SourceFileUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SourceFileUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SourceFileUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SourceFileService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SourceFile(123);
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
        const entity = new SourceFile();
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
