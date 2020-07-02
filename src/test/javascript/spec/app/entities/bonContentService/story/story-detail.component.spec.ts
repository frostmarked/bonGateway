import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { StoryDetailComponent } from 'app/entities/bonContentService/story/story-detail.component';
import { Story } from 'app/shared/model/bonContentService/story.model';

describe('Component Tests', () => {
  describe('Story Management Detail Component', () => {
    let comp: StoryDetailComponent;
    let fixture: ComponentFixture<StoryDetailComponent>;
    const route = ({ data: of({ story: new Story(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [StoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(StoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load story on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.story).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
