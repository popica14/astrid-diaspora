import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { AstridUserUpdateComponent } from 'app/entities/astrid-user/astrid-user-update.component';
import { AstridUserService } from 'app/entities/astrid-user/astrid-user.service';
import { AstridUser } from 'app/shared/model/astrid-user.model';

describe('Component Tests', () => {
  describe('AstridUser Management Update Component', () => {
    let comp: AstridUserUpdateComponent;
    let fixture: ComponentFixture<AstridUserUpdateComponent>;
    let service: AstridUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [AstridUserUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AstridUserUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AstridUserUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AstridUserService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AstridUser(123);
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
        const entity = new AstridUser();
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
