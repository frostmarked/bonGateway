import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FragmentService } from 'app/entities/bonContentService/fragment/fragment.service';
import { IFragment, Fragment } from 'app/shared/model/bonContentService/fragment.model';
import { FragmentTemplate } from 'app/shared/model/enumerations/fragment-template.model';
import { UserRole } from 'app/shared/model/enumerations/user-role.model';

describe('Service Tests', () => {
  describe('Fragment Service', () => {
    let injector: TestBed;
    let service: FragmentService;
    let httpMock: HttpTestingController;
    let elemDefault: IFragment;
    let expectedResult: IFragment | IFragment[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FragmentService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Fragment(
        0,
        FragmentTemplate.V1,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        UserRole.ROLE_ADMIN
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Fragment', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Fragment()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Fragment', () => {
        const returnedFromService = Object.assign(
          {
            template: 'BBBBBB',
            name: 'BBBBBB',
            title: 'BBBBBB',
            ingress: 'BBBBBB',
            body: 'BBBBBB',
            image: 'BBBBBB',
            caption: 'BBBBBB',
            width: 1,
            height: 1,
            orderNo: 1,
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

      it('should return a list of Fragment', () => {
        const returnedFromService = Object.assign(
          {
            template: 'BBBBBB',
            name: 'BBBBBB',
            title: 'BBBBBB',
            ingress: 'BBBBBB',
            body: 'BBBBBB',
            image: 'BBBBBB',
            caption: 'BBBBBB',
            width: 1,
            height: 1,
            orderNo: 1,
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

      it('should delete a Fragment', () => {
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
