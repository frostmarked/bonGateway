import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BonGatewayTestModule } from '../../../../test.module';
import { TagDetailComponent } from 'app/entities/bonContentService/tag/tag-detail.component';
import { Tag } from 'app/shared/model/bonContentService/tag.model';

describe('Component Tests', () => {
  describe('Tag Management Detail Component', () => {
    let comp: TagDetailComponent;
    let fixture: ComponentFixture<TagDetailComponent>;
    const route = ({ data: of({ tag: new Tag(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BonGatewayTestModule],
        declarations: [TagDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TagDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TagDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tag on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tag).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
