import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LocalizedService } from 'app/entities/bonContentService/localized/localized.service';
import { ILocalized, Localized } from 'app/shared/model/bonContentService/localized.model';
import { UserRole } from 'app/shared/model/enumerations/user-role.model';

describe('Service Tests', () => {
  describe('Localized Service', () => {
    let injector: TestBed;
    let service: LocalizedService;
    let httpMock: HttpTestingController;
    let elemDefault: ILocalized;
    let expectedResult: ILocalized | ILocalized[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LocalizedService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Localized(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', UserRole.ROLE_ADMIN);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Localized', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Localized()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Localized', () => {
        const returnedFromService = Object.assign(
          {
            i18n: 'BBBBBB',
            title: 'BBBBBB',
            ingress: 'BBBBBB',
            body: 'BBBBBB',
            caption: 'BBBBBB',
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

      it('should return a list of Localized', () => {
        const returnedFromService = Object.assign(
          {
            i18n: 'BBBBBB',
            title: 'BBBBBB',
            ingress: 'BBBBBB',
            body: 'BBBBBB',
            caption: 'BBBBBB',
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

      it('should delete a Localized', () => {
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
