import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { CattleDetailComponent } from 'app/entities/bonLivestockService/cattle/cattle-detail.component';
import { Cattle } from 'app/shared/model/bonLivestockService/cattle.model';

describe('Component Tests', () => {
  describe('Cattle Management Detail Component', () => {
    let comp: CattleDetailComponent;
    let fixture: ComponentFixture<CattleDetailComponent>;
    const route = ({ data: of({ cattle: new Cattle(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [CattleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CattleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CattleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load cattle on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cattle).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
