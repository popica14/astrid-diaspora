import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAstridUser } from 'app/shared/model/astrid-user.model';

@Component({
  selector: 'jhi-astrid-user-detail',
  templateUrl: './astrid-user-detail.component.html',
})
export class AstridUserDetailComponent implements OnInit {
  astridUser: IAstridUser | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ astridUser }) => (this.astridUser = astridUser));
  }

  previousState(): void {
    window.history.back();
  }
}
