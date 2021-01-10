import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { AstridProjectSuggestionComponent } from 'app/entities/astrid-project-suggestion/astrid-project-suggestion.component';
import { AstridProjectSuggestionService } from 'app/entities/astrid-project-suggestion/astrid-project-suggestion.service';
import { AstridProjectSuggestion } from 'app/shared/model/astrid-project-suggestion.model';

describe('Component Tests', () => {
  describe('AstridProjectSuggestion Management Component', () => {
    let comp: AstridProjectSuggestionComponent;
    let fixture: ComponentFixture<AstridProjectSuggestionComponent>;
    let service: AstridProjectSuggestionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [AstridProjectSuggestionComponent],
      })
        .overrideTemplate(AstridProjectSuggestionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AstridProjectSuggestionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AstridProjectSuggestionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AstridProjectSuggestion(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.astridProjectSuggestions && comp.astridProjectSuggestions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
