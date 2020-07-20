import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { PastureDetailComponent } from 'app/entities/bonLivestockService/pasture/pasture-detail.component';
import { Pasture } from 'app/shared/model/bonLivestockService/pasture.model';

describe('Component Tests', () => {
  describe('Pasture Management Detail Component', () => {
    let comp: PastureDetailComponent;
    let fixture: ComponentFixture<PastureDetailComponent>;
    const route = ({ data: of({ pasture: new Pasture(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [PastureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PastureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PastureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load pasture on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pasture).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
