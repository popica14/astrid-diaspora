import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { EntityCreationDetailComponent } from 'app/entities/entity-creation/entity-creation-detail.component';
import { EntityCreation } from 'app/shared/model/entity-creation.model';

describe('Component Tests', () => {
  describe('EntityCreation Management Detail Component', () => {
    let comp: EntityCreationDetailComponent;
    let fixture: ComponentFixture<EntityCreationDetailComponent>;
    const route = ({ data: of({ entityCreation: new EntityCreation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [EntityCreationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EntityCreationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityCreationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entityCreation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityCreation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
