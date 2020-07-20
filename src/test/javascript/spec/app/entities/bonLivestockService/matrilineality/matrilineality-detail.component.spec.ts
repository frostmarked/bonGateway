import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { MatrilinealityDetailComponent } from 'app/entities/bonLivestockService/matrilineality/matrilineality-detail.component';
import { Matrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';

describe('Component Tests', () => {
  describe('Matrilineality Management Detail Component', () => {
    let comp: MatrilinealityDetailComponent;
    let fixture: ComponentFixture<MatrilinealityDetailComponent>;
    const route = ({ data: of({ matrilineality: new Matrilineality(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [MatrilinealityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MatrilinealityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MatrilinealityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load matrilineality on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.matrilineality).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
