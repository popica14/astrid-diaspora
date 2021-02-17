import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAstridProject } from 'app/shared/model/astrid-project.model';
import { EntityLastModificationService } from '../entity-last-modification/entity-last-modification.service';
import { EntityCreationService } from '../entity-creation/entity-creation.service';
import { IEntityLastModification } from 'app/shared/model/entity-last-modification.model';
import { HttpResponse } from '@angular/common/http';
import { IEntityCreation } from 'app/shared/model/entity-creation.model';

@Component({
  selector: 'jhi-astrid-project-detail',
  templateUrl: './astrid-project-detail.component.html',
})
export class AstridProjectDetailComponent implements OnInit {
  astridProject: IAstridProject | null = null;

  lastModifiedDate: String = '';
  createdDate: String = '';
  statusDeadline: String = '';

  entitycreation: IEntityCreation = {
    id: 0,
    created: undefined,
    createdByLogin: '',
    createdById: 0,
    astridProjectId: 0,
  };

  entityLastModification: IEntityLastModification = {
    id: 0,
    lastModified: undefined,
    lastModifiedByLogin: '',
    lastModifiedById: 0,
    astridProjectId: 0,
  };

  constructor(
    protected dataUtils: JhiDataUtils,
    protected activatedRoute: ActivatedRoute,
    protected entityCreationService: EntityCreationService,
    protected entityLastModificationService: EntityLastModificationService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ astridProject }) => this.handleAstridProject(astridProject));

    if (this.astridProject !== null && this.astridProject.entityLastModificationId !== undefined) {
      this.entityLastModificationService
        .find(this.astridProject.entityLastModificationId)
        .subscribe((res: HttpResponse<IEntityLastModification>) => this.processLastModifieDate(res));
    }

    if (this.astridProject !== null && this.astridProject.entityCreationId !== undefined) {
      this.entityCreationService
        .find(this.astridProject.entityCreationId)
        .subscribe((res: HttpResponse<IEntityCreation>) => this.processCreatedDate(res));
    }
  }

  private handleAstridProject(astridProject: any): void {
    this.astridProject = astridProject;
  }

  processLastModifieDate(res: HttpResponse<IEntityLastModification>): void {
    this.entityLastModification = res.body || {};
  }

  processCreatedDate(res: HttpResponse<IEntityCreation>): void {
    this.entitycreation = res.body || {};
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
