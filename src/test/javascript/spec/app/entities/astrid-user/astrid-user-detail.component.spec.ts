import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ProjectsOverviewTestModule } from '../../../test.module';
import { AstridUserDetailComponent } from 'app/entities/astrid-user/astrid-user-detail.component';
import { AstridUser } from 'app/shared/model/astrid-user.model';

describe('Component Tests', () => {
  describe('AstridUser Management Detail Component', () => {
    let comp: AstridUserDetailComponent;
    let fixture: ComponentFixture<AstridUserDetailComponent>;
    const route = ({ data: of({ astridUser: new AstridUser(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ProjectsOverviewTestModule],
        declarations: [AstridUserDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AstridUserDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AstridUserDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load astridUser on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.astridUser).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
