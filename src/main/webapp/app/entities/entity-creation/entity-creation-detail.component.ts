import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntityCreation } from 'app/shared/model/entity-creation.model';

@Component({
  selector: 'jhi-entity-creation-detail',
  templateUrl: './entity-creation-detail.component.html',
})
export class EntityCreationDetailComponent implements OnInit {
  entityCreation: IEntityCreation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entityCreation }) => (this.entityCreation = entityCreation));
  }

  previousState(): void {
    window.history.back();
  }
}
