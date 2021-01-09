import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntityLastModification } from 'app/shared/model/entity-last-modification.model';

@Component({
  selector: 'jhi-entity-last-modification-detail',
  templateUrl: './entity-last-modification-detail.component.html',
})
export class EntityLastModificationDetailComponent implements OnInit {
  entityLastModification: IEntityLastModification | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityLastModification }) => (this.entityLastModification = entityLastModification));
  }

  previousState(): void {
    window.history.back();
  }
}
