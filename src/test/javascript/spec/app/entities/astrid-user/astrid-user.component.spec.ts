import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { AstridUserComponent } from 'app/entities/astrid-user/astrid-user.component';
import { AstridUserService } from 'app/entities/astrid-user/astrid-user.service';
import { AstridUser } from 'app/shared/model/astrid-user.model';

describe('Component Tests', () => {
  describe('AstridUser Management Component', () => {
    let comp: AstridUserComponent;
    let fixture: ComponentFixture<AstridUserComponent>;
    let service: AstridUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [AstridUserComponent],
      })
        .overrideTemplate(AstridUserComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AstridUserComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AstridUserService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AstridUser(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.astridUsers && comp.astridUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
