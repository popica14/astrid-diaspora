import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { AstridProjectSuggestionDetailComponent } from 'app/entities/astrid-project-suggestion/astrid-project-suggestion-detail.component';
import { AstridProjectSuggestion } from 'app/shared/model/astrid-project-suggestion.model';

describe('Component Tests', () => {
  describe('AstridProjectSuggestion Management Detail Component', () => {
    let comp: AstridProjectSuggestionDetailComponent;
    let fixture: ComponentFixture<AstridProjectSuggestionDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ astridProjectSuggestion: new AstridProjectSuggestion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [AstridProjectSuggestionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AstridProjectSuggestionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AstridProjectSuggestionDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load astridProjectSuggestion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.astridProjectSuggestion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
