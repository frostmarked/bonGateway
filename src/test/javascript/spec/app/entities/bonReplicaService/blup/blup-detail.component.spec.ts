import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { BlupDetailComponent } from 'app/entities/bonReplicaService/blup/blup-detail.component';
import { Blup } from 'app/shared/model/bonReplicaService/blup.model';

describe('Component Tests', () => {
  describe('Blup Management Detail Component', () => {
    let comp: BlupDetailComponent;
    let fixture: ComponentFixture<BlupDetailComponent>;
    const route = ({ data: of({ blup: new Blup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [BlupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BlupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BlupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load blup on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.blup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
