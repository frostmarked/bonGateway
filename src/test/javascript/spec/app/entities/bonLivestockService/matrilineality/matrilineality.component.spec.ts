import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { BonGatewayTestModule } from '../../../../test.module';
import { MatrilinealityComponent } from 'app/entities/bonLivestockService/matrilineality/matrilineality.component';
import { MatrilinealityService } from 'app/entities/bonLivestockService/matrilineality/matrilineality.service';
import { Matrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';

describe('Component Tests', () => {
  describe('Matrilineality Management Component', () => {
    let comp: MatrilinealityComponent;
    let fixture: ComponentFixture<MatrilinealityComponent>;
    let service: MatrilinealityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [MatrilinealityComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(MatrilinealityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MatrilinealityComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MatrilinealityService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Matrilineality(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.matrilinealities && comp.matrilinealities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Matrilineality(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.matrilinealities && comp.matrilinealities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
