import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MatrilinealityService } from 'app/entities/bonLivestockService/matrilineality/matrilineality.service';
import { IMatrilineality, Matrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';
import { UserRole } from 'app/shared/model/enumerations/user-role.model';

describe('Service Tests', () => {
  describe('Matrilineality Service', () => {
    let injector: TestBed;
    let service: MatrilinealityService;
    let httpMock: HttpTestingController;
    let elemDefault: IMatrilineality;
    let expectedResult: IMatrilineality | IMatrilineality[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MatrilinealityService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Matrilineality(
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
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

      it('should create a Matrilineality', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Matrilineality()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Matrilineality', () => {
        const returnedFromService = Object.assign(
          {
            familyname: 'BBBBBB',
            earTagId: 1,
            name: 'BBBBBB',
            country: 'BBBBBB',
            description: 'BBBBBB',
            cattleNameRegexPattern: 'BBBBBB',
            patriId: 1,
            patriName: 'BBBBBB',
            patriCountry: 'BBBBBB',
            polled: true,
            storyHandle: 'BBBBBB',
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

      it('should return a list of Matrilineality', () => {
        const returnedFromService = Object.assign(
          {
            familyname: 'BBBBBB',
            earTagId: 1,
            name: 'BBBBBB',
            country: 'BBBBBB',
            description: 'BBBBBB',
            cattleNameRegexPattern: 'BBBBBB',
            patriId: 1,
            patriName: 'BBBBBB',
            patriCountry: 'BBBBBB',
            polled: true,
            storyHandle: 'BBBBBB',
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

      it('should delete a Matrilineality', () => {
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
