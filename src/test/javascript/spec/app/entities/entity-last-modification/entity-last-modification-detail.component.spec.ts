import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { EntityLastModificationDetailComponent } from 'app/entities/entity-last-modification/entity-last-modification-detail.component';
import { EntityLastModification } from 'app/shared/model/entity-last-modification.model';

describe('Component Tests', () => {
  describe('EntityLastModification Management Detail Component', () => {
    let comp: EntityLastModificationDetailComponent;
    let fixture: ComponentFixture<EntityLastModificationDetailComponent>;
    const route = ({ data: of({ entityLastModification: new EntityLastModification(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [EntityLastModificationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EntityLastModificationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityLastModificationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load entityLastModification on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityLastModification).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
