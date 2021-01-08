import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { AstridProjectDetailComponent } from 'app/entities/astrid-project/astrid-project-detail.component';
import { AstridProject } from 'app/shared/model/astrid-project.model';

describe('Component Tests', () => {
  describe('AstridProject Management Detail Component', () => {
    let comp: AstridProjectDetailComponent;
    let fixture: ComponentFixture<AstridProjectDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ astridProject: new AstridProject(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [AstridProjectDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AstridProjectDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AstridProjectDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load astridProject on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.astridProject).toEqual(jasmine.objectContaining({ id: 123 }));
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
