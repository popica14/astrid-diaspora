import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { EntityLastModificationUpdateComponent } from 'app/entities/entity-last-modification/entity-last-modification-update.component';
import { EntityLastModificationService } from 'app/entities/entity-last-modification/entity-last-modification.service';
import { EntityLastModification } from 'app/shared/model/entity-last-modification.model';

describe('Component Tests', () => {
  describe('EntityLastModification Management Update Component', () => {
    let comp: EntityLastModificationUpdateComponent;
    let fixture: ComponentFixture<EntityLastModificationUpdateComponent>;
    let service: EntityLastModificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [EntityLastModificationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EntityLastModificationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityLastModificationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityLastModificationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityLastModification(123);
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
        const entity = new EntityLastModification();
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
