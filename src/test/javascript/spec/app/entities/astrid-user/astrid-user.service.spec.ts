import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AstridUserService } from 'app/entities/astrid-user/astrid-user.service';
import { IAstridUser, AstridUser } from 'app/shared/model/astrid-user.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { Education } from 'app/shared/model/enumerations/education.model';

describe('Service Tests', () => {
  describe('AstridUser Service', () => {
    let injector: TestBed;
    let service: AstridUserService;
    let httpMock: HttpTestingController;
    let elemDefault: IAstridUser;
    let expectedResult: IAstridUser | IAstridUser[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AstridUserService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AstridUser(0, 'AAAAAAA', 'AAAAAAA', Gender.FEMALE, currentDate, Education.DOCTORATE);
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

      it('should create a AstridUser', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            birthDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthDate: currentDate,
          },
          returnedFromService
        );

        service.create(new AstridUser()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AstridUser', () => {
        const returnedFromService = Object.assign(
          {
            phoneNumber: 'BBBBBB',
            residency: 'BBBBBB',
            gender: 'BBBBBB',
            birthDate: currentDate.format(DATE_TIME_FORMAT),
            highestEducation: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            birthDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AstridUser', () => {
        const returnedFromService = Object.assign(
          {
            phoneNumber: 'BBBBBB',
            residency: 'BBBBBB',
            gender: 'BBBBBB',
            birthDate: currentDate.format(DATE_TIME_FORMAT),
            highestEducation: 'BBBBBB',
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

      it('should delete a AstridUser', () => {
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
