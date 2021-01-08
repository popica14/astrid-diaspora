import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAstridProject } from 'app/shared/model/astrid-project.model';

@Component({
  selector: 'jhi-astrid-project-detail',
  templateUrl: './astrid-project-detail.component.html',
})
export class AstridProjectDetailComponent implements OnInit {
  astridProject: IAstridProject | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ astridProject }) => (this.astridProject = astridProject));
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
