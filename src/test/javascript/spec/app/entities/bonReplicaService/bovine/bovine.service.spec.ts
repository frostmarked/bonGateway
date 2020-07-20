import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BovineService } from 'app/entities/bonReplicaService/bovine/bovine.service';
import { IBovine, Bovine } from 'app/shared/model/bonReplicaService/bovine.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { BovineStatus } from 'app/shared/model/enumerations/bovine-status.model';
import { HornStatus } from 'app/shared/model/enumerations/horn-status.model';

describe('Service Tests', () => {
  describe('Bovine Service', () => {
    let injector: TestBed;
    let service: BovineService;
    let httpMock: HttpTestingController;
    let elemDefault: IBovine;
    let expectedResult: IBovine | IBovine[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BovineService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Bovine(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        currentDate,
        Gender.HEIFER,
        'AAAAAAA',
        BovineStatus.UNKNOWN,
        HornStatus.UNKNOWN,
        0,
        0,
        0,
        0,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            birthDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of Bovine', () => {
        const returnedFromService = Object.assign(
          {
            earTagId: 1,
            masterIdentifier: 'BBBBBB',
            country: 'BBBBBB',
            herdId: 1,
            birthDate: currentDate.format(DATE_TIME_FORMAT),
            gender: 'BBBBBB',
            name: 'BBBBBB',
            bovineStatus: 'BBBBBB',
            hornStatus: 'BBBBBB',
            matriId: 1,
            patriId: 1,
            weight0: 1,
            weight200: 1,
            weight365: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
