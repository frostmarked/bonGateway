import { ComponentFixture, TestBed, async } from '@angular/core/testing';

import { BonGatewayTestModule } from '../../test.module';
import { HomeComponent } from 'app/home/home.component';

describe('Component Tests', () => {
  describe('Home Component', () => {
    let comp: HomeComponent;
    let fixture: ComponentFixture<HomeComponent>;

    beforeEach(async(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [HomeComponent],
      })
        .overrideTemplate(HomeComponent, '')
        .compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(HomeComponent);
      comp = fixture.componentInstance;
    });

    it('Should not do anything on init, for now', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect('me').not.toBe('I');
    });
  });
});
