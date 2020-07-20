import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SourceFileService } from 'app/entities/bonReplicaService/source-file/source-file.service';
import { ISourceFile, SourceFile } from 'app/shared/model/bonReplicaService/source-file.model';

describe('Service Tests', () => {
  describe('SourceFile Service', () => {
    let injector: TestBed;
    let service: SourceFileService;
    let httpMock: HttpTestingController;
    let elemDefault: ISourceFile;
    let expectedResult: ISourceFile | ISourceFile[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SourceFileService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SourceFile(0, 'AAAAAAA', 'image/png', 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            processed: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SourceFile', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            processed: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            processed: currentDate,
          },
          returnedFromService
        );

        service.create(new SourceFile()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SourceFile', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            zipFile: 'BBBBBB',
            processed: currentDate.format(DATE_TIME_FORMAT),
            outcome: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            processed: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SourceFile', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            zipFile: 'BBBBBB',
            processed: currentDate.format(DATE_TIME_FORMAT),
            outcome: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            processed: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SourceFile', () => {
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
