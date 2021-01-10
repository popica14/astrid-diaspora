import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { AstridProjectSuggestionUpdateComponent } from 'app/entities/astrid-project-suggestion/astrid-project-suggestion-update.component';
import { AstridProjectSuggestionService } from 'app/entities/astrid-project-suggestion/astrid-project-suggestion.service';
import { AstridProjectSuggestion } from 'app/shared/model/astrid-project-suggestion.model';

describe('Component Tests', () => {
  describe('AstridProjectSuggestion Management Update Component', () => {
    let comp: AstridProjectSuggestionUpdateComponent;
    let fixture: ComponentFixture<AstridProjectSuggestionUpdateComponent>;
    let service: AstridProjectSuggestionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [AstridProjectSuggestionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AstridProjectSuggestionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AstridProjectSuggestionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AstridProjectSuggestionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AstridProjectSuggestion(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AstridProjectSuggestion();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
