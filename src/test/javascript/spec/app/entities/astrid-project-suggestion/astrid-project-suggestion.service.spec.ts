import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AstridProjectSuggestionService } from 'app/entities/astrid-project-suggestion/astrid-project-suggestion.service';
import { IAstridProjectSuggestion, AstridProjectSuggestion } from 'app/shared/model/astrid-project-suggestion.model';

describe('Service Tests', () => {
  describe('AstridProjectSuggestion Service', () => {
    let injector: TestBed;
    let service: AstridProjectSuggestionService;
    let httpMock: HttpTestingController;
    let elemDefault: IAstridProjectSuggestion;
    let expectedResult: IAstridProjectSuggestion | IAstridProjectSuggestion[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AstridProjectSuggestionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AstridProjectSuggestion(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
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
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AstridProjectSuggestion', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new AstridProjectSuggestion()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AstridProjectSuggestion', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortDescription: 'BBBBBB',
            documentation: 'BBBBBB',
            goal: 'BBBBBB',
            documentation1: 'BBBBBB',
            documentation2: 'BBBBBB',
            documentation3: 'BBBBBB',
            documentation4: 'BBBBBB',
            documentation5: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AstridProjectSuggestion', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            shortDescription: 'BBBBBB',
            documentation: 'BBBBBB',
            goal: 'BBBBBB',
            documentation1: 'BBBBBB',
            documentation2: 'BBBBBB',
            documentation3: 'BBBBBB',
            documentation4: 'BBBBBB',
            documentation5: 'BBBBBB',
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

      it('should delete a AstridProjectSuggestion', () => {
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
