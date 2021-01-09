import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { EntityCreationUpdateComponent } from 'app/entities/entity-creation/entity-creation-update.component';
import { EntityCreationService } from 'app/entities/entity-creation/entity-creation.service';
import { EntityCreation } from 'app/shared/model/entity-creation.model';

describe('Component Tests', () => {
  describe('EntityCreation Management Update Component', () => {
    let comp: EntityCreationUpdateComponent;
    let fixture: ComponentFixture<EntityCreationUpdateComponent>;
    let service: EntityCreationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [EntityCreationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EntityCreationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityCreationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityCreationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityCreation(123);
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
        const entity = new EntityCreation();
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
