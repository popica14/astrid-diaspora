import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { EntityLastModificationComponent } from 'app/entities/entity-last-modification/entity-last-modification.component';
import { EntityLastModificationService } from 'app/entities/entity-last-modification/entity-last-modification.service';
import { EntityLastModification } from 'app/shared/model/entity-last-modification.model';

describe('Component Tests', () => {
  describe('EntityLastModification Management Component', () => {
    let comp: EntityLastModificationComponent;
    let fixture: ComponentFixture<EntityLastModificationComponent>;
    let service: EntityLastModificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [EntityLastModificationComponent],
      })
        .overrideTemplate(EntityLastModificationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityLastModificationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityLastModificationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EntityLastModification(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entityLastModifications && comp.entityLastModifications[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
