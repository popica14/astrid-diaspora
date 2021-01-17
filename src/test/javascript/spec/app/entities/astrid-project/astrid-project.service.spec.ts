import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AstridProjectService } from 'app/entities/astrid-project/astrid-project.service';
import { IAstridProject, AstridProject } from 'app/shared/model/astrid-project.model';

describe('Service Tests', () => {
  describe('AstridProject Service', () => {
    let injector: TestBed;
    let service: AstridProjectService;
    let httpMock: HttpTestingController;
    let elemDefault: IAstridProject;
    let expectedResult: IAstridProject | IAstridProject[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AstridProjectService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AstridProject(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'image/png',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            statusDeadline: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AstridProject', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            statusDeadline: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            statusDeadline: currentDate,
          },
          returnedFromService
        );

        service.create(new AstridProject()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AstridProject', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortDescription: 'BBBBBB',
            neededAmount: 'BBBBBB',
            currentAmount: 'BBBBBB',
            supporters: 1,
            goal: 'BBBBBB',
            statusReason: 'BBBBBB',
            statusDeadline: currentDate.format(DATE_TIME_FORMAT),
            documentation1: 'BBBBBB',
            documentation2: 'BBBBBB',
            documentation3: 'BBBBBB',
            documentation4: 'BBBBBB',
            documentation5: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            statusDeadline: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AstridProject', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortDescription: 'BBBBBB',
            neededAmount: 'BBBBBB',
            currentAmount: 'BBBBBB',
            supporters: 1,
            goal: 'BBBBBB',
            statusReason: 'BBBBBB',
            statusDeadline: currentDate.format(DATE_TIME_FORMAT),
            documentation1: 'BBBBBB',
            documentation2: 'BBBBBB',
            documentation3: 'BBBBBB',
            documentation4: 'BBBBBB',
            documentation5: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            statusDeadline: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AstridProject', () => {
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
