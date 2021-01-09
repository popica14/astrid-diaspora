import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { ProjectStatusComponent } from 'app/entities/project-status/project-status.component';
import { ProjectStatusService } from 'app/entities/project-status/project-status.service';
import { ProjectStatus } from 'app/shared/model/project-status.model';

describe('Component Tests', () => {
  describe('ProjectStatus Management Component', () => {
    let comp: ProjectStatusComponent;
    let fixture: ComponentFixture<ProjectStatusComponent>;
    let service: ProjectStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [ProjectStatusComponent],
      })
        .overrideTemplate(ProjectStatusComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProjectStatusComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProjectStatusService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProjectStatus(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.projectStatuses && comp.projectStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
