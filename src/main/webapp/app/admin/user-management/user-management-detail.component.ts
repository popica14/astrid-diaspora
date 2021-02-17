import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { User } from 'app/core/user/user.model';
import { AstridUserService } from 'app/entities/astrid-user/astrid-user.service';
import { AstridUser, IAstridUser } from 'app/shared/model/astrid-user.model';

@Component({
  selector: 'jhi-user-mgmt-detail',
  templateUrl: './user-management-detail.component.html',
})
export class UserManagementDetailComponent implements OnInit {
  user: User | null = null;
  astridUser: AstridUser = {};

  constructor(private route: ActivatedRoute, private astridUserService: AstridUserService) {}

  ngOnInit(): void {
    this.route.data.subscribe(({ user }) => this.udpateForm(user));
  }
  udpateForm(user: any): void {
    this.user = user;

    if (this.user !== null && this.user.id !== null) {
      this.astridUserService
        .findExtendedUserByUserId(this.user.id)
        .subscribe((res: HttpResponse<IAstridUser>) => this.processExtendedUser(res));
    }
  }
  processExtendedUser(res: HttpResponse<IAstridUser>): void {
    this.astridUser = res.body || {};
  }
}
