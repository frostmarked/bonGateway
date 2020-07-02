import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JournalEntryService } from 'app/entities/bonReplicaService/journal-entry/journal-entry.service';
import { IJournalEntry, JournalEntry } from 'app/shared/model/bonReplicaService/journal-entry.model';
import { EntryStatus } from 'app/shared/model/enumerations/entry-status.model';

describe('Service Tests', () => {
  describe('JournalEntry Service', () => {
    let injector: TestBed;
    let service: JournalEntryService;
    let httpMock: HttpTestingController;
    let elemDefault: IJournalEntry;
    let expectedResult: IJournalEntry | IJournalEntry[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(JournalEntryService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new JournalEntry(0, EntryStatus.FOD, currentDate, currentDate, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_TIME_FORMAT),
            edited: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of JournalEntry', () => {
        const returnedFromService = Object.assign(
          {
            status: 'BBBBBB',
            date: currentDate.format(DATE_TIME_FORMAT),
            edited: currentDate.format(DATE_TIME_FORMAT),
            herdId: 1,
            newHerdId: 1,
            subState1: 1,
            subState2: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
            edited: currentDate,
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
