import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BlupService } from 'app/entities/bonReplicaService/blup/blup.service';
import { IBlup, Blup } from 'app/shared/model/bonReplicaService/blup.model';

describe('Service Tests', () => {
  describe('Blup Service', () => {
    let injector: TestBed;
    let service: BlupService;
    let httpMock: HttpTestingController;
    let elemDefault: IBlup;
    let expectedResult: IBlup | IBlup[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BlupService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Blup(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of Blup', () => {
        const returnedFromService = Object.assign(
          {
            t0: 1,
            d0: 1,
            m0: 1,
            t200: 1,
            d200: 1,
            m200: 1,
            t365: 1,
            d365: 1,
            total: 1,
            status: 'BBBBBB',
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
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
