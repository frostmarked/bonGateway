import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { BovineDetailComponent } from 'app/entities/bonReplicaService/bovine/bovine-detail.component';
import { Bovine } from 'app/shared/model/bonReplicaService/bovine.model';

describe('Component Tests', () => {
  describe('Bovine Management Detail Component', () => {
    let comp: BovineDetailComponent;
    let fixture: ComponentFixture<BovineDetailComponent>;
    const route = ({ data: of({ bovine: new Bovine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [BovineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BovineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BovineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bovine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bovine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
