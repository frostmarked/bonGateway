import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PhotoService } from 'app/entities/bonLivestockService/photo/photo.service';
import { IPhoto, Photo } from 'app/shared/model/bonLivestockService/photo.model';
import { UserRole } from 'app/shared/model/enumerations/user-role.model';

describe('Service Tests', () => {
  describe('Photo Service', () => {
    let injector: TestBed;
    let service: PhotoService;
    let httpMock: HttpTestingController;
    let elemDefault: IPhoto;
    let expectedResult: IPhoto | IPhoto[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PhotoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Photo(0, 'image/png', 'AAAAAAA', 'AAAAAAA', 0, 0, currentDate, UserRole.ROLE_ADMIN);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            taken: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Photo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            taken: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            taken: currentDate,
          },
          returnedFromService
        );

        service.create(new Photo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Photo', () => {
        const returnedFromService = Object.assign(
          {
            image: 'BBBBBB',
            caption: 'BBBBBB',
            height: 1,
            width: 1,
            taken: currentDate.format(DATE_TIME_FORMAT),
            visibility: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            taken: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Photo', () => {
        const returnedFromService = Object.assign(
          {
            image: 'BBBBBB',
            caption: 'BBBBBB',
            height: 1,
            width: 1,
            taken: currentDate.format(DATE_TIME_FORMAT),
            visibility: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            taken: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Photo', () => {
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
