import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { EntityCreationComponent } from 'app/entities/entity-creation/entity-creation.component';
import { EntityCreationService } from 'app/entities/entity-creation/entity-creation.service';
import { EntityCreation } from 'app/shared/model/entity-creation.model';

describe('Component Tests', () => {
  describe('EntityCreation Management Component', () => {
    let comp: EntityCreationComponent;
    let fixture: ComponentFixture<EntityCreationComponent>;
    let service: EntityCreationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [EntityCreationComponent],
      })
        .overrideTemplate(EntityCreationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityCreationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityCreationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EntityCreation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entityCreations && comp.entityCreations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
