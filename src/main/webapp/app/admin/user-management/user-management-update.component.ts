import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { LANGUAGES } from 'app/core/language/language.constants';
import { UserExtended } from 'app/core/user/user-extended.model';
import { User } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { AstridUserService } from 'app/entities/astrid-user/astrid-user.service';
import { AstridUser, IAstridUser } from 'app/shared/model/astrid-user.model';

@Component({
  selector: 'jhi-user-mgmt-update',
  templateUrl: './user-management-update.component.html',
})
export class UserManagementUpdateComponent implements OnInit {
  user!: User;
  languages = LANGUAGES;
  authorities: string[] = [];
  isSaving = false;
  userExtended: AstridUser = {};

  editForm = this.fb.group({
    id: [],
    login: [
      '',
      [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(50),
        Validators.pattern('^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$'),
      ],
    ],
    firstName: ['', [Validators.maxLength(50)]],
    lastName: ['', [Validators.maxLength(50)]],
    email: ['', [Validators.minLength(5), Validators.maxLength(254), Validators.email]],
    activated: [],
    langKey: [],
    authorities: [],
    phoneNumber: [null, [Validators.required]],
    residency: [null, [Validators.required]],
    gender: [null, [Validators.required]],
    birthDate: [null, [Validators.required]],
    highestEducation: [null, [Validators.required]],
  });

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private astridUserService: AstridUserService
  ) {}

  ngOnInit(): void {
    this.route.data.subscribe(({ user }) => {
      if (user) {
        this.user = user;
        if (this.user.id === undefined) {
          this.user.activated = true;
        }
        if (this.user.id != null) {
          this.astridUserService
            .findExtendedUserByUserId(this.user.id)
            .subscribe((res: HttpResponse<IAstridUser>) => this.processExtendedUser(user, res));
        }
      }
    });

    this.userService.authorities().subscribe(authorities => {
      this.authorities = authorities;
    });
  }
  processExtendedUser(user: User, res: HttpResponse<IAstridUser>): void {
    this.userExtended = res.body || {};
    this.updateForm(user, this.userExtended);
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    this.updateUser(this.user);
    if (this.user.id !== undefined) {
      this.userService.update(this.user).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    } else {
      this.userService.create(this.user).subscribe(
        () => this.onSaveSuccess(),
        () => this.onSaveError()
      );
    }
  }

  private updateForm(user: User, astridUSer: AstridUser): void {
    this.editForm.patchValue({
      id: user.id,
      login: user.login,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      activated: user.activated,
      langKey: user.langKey,
      authorities: user.authorities,
    });

    if (astridUSer !== undefined) {
      this.editForm.patchValue({
        gender: astridUSer.gender,
        highestEducation: astridUSer.highestEducation,
        phoneNumber: astridUSer.phoneNumber,
        birthDate: astridUSer.birthDate,
        residency: astridUSer.residency,
      });
    }
  }

  private updateUser(user: UserExtended): void {
    user.login = this.editForm.get(['login'])!.value;
    user.firstName = this.editForm.get(['firstName'])!.value;
    user.lastName = this.editForm.get(['lastName'])!.value;
    user.email = this.editForm.get(['email'])!.value;
    user.activated = this.editForm.get(['activated'])!.value;
    user.langKey = this.editForm.get(['langKey'])!.value;
    user.authorities = this.editForm.get(['authorities'])!.value;
    user.gender = this.editForm.get(['gender'])!.value;
    user.highestEducation = this.editForm.get(['highestEducation'])!.value;
    user.phoneNumber = this.editForm.get(['phoneNumber'])!.value;
    user.residency = this.editForm.get(['residency'])!.value;
    user.birthDate = this.editForm.get(['birthDate'])!.value;
  }

  private onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError(): void {
    this.isSaving = false;
  }
}
