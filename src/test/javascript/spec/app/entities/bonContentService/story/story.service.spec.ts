import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { StoryService } from 'app/entities/bonContentService/story/story.service';
import { IStory, Story } from 'app/shared/model/bonContentService/story.model';
import { StoryCategory } from 'app/shared/model/enumerations/story-category.model';
import { UserRole } from 'app/shared/model/enumerations/user-role.model';

describe('Service Tests', () => {
  describe('Story Service', () => {
    let injector: TestBed;
    let service: StoryService;
    let httpMock: HttpTestingController;
    let elemDefault: IStory;
    let expectedResult: IStory | IStory[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(StoryService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Story(0, StoryCategory.NEWS, 'AAAAAAA', UserRole.ROLE_ADMIN);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Story', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Story()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Story', () => {
        const returnedFromService = Object.assign(
          {
            category: 'BBBBBB',
            name: 'BBBBBB',
            visibility: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Story', () => {
        const returnedFromService = Object.assign(
          {
            category: 'BBBBBB',
            name: 'BBBBBB',
            visibility: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Story', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
