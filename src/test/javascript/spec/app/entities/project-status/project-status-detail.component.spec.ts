import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { ProjectStatusDetailComponent } from 'app/entities/project-status/project-status-detail.component';
import { ProjectStatus } from 'app/shared/model/project-status.model';

describe('Component Tests', () => {
  describe('ProjectStatus Management Detail Component', () => {
    let comp: ProjectStatusDetailComponent;
    let fixture: ComponentFixture<ProjectStatusDetailComponent>;
    const route = ({ data: of({ projectStatus: new ProjectStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [ProjectStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProjectStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProjectStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load projectStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.projectStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
