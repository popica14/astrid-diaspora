import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { ProjectStatusUpdateComponent } from 'app/entities/project-status/project-status-update.component';
import { ProjectStatusService } from 'app/entities/project-status/project-status.service';
import { ProjectStatus } from 'app/shared/model/project-status.model';

describe('Component Tests', () => {
  describe('ProjectStatus Management Update Component', () => {
    let comp: ProjectStatusUpdateComponent;
    let fixture: ComponentFixture<ProjectStatusUpdateComponent>;
    let service: ProjectStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [ProjectStatusUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProjectStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProjectStatus(123);
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
        const entity = new ProjectStatus();
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
