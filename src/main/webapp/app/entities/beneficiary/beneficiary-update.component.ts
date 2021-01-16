import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBeneficiary, Beneficiary } from 'app/shared/model/beneficiary.model';
import { BeneficiaryService } from './beneficiary.service';

@Component({
  selector: 'jhi-beneficiary-update',
  templateUrl: './beneficiary-update.component.html',
})
export class BeneficiaryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    type: [null, [Validators.required]],
    address: [],
    phoneNumber: [null, [Validators.required]],
    email: [null, [Validators.required]],
    contactPerson: [],
  });

  constructor(protected beneficiaryService: BeneficiaryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ beneficiary }) => {
      this.updateForm(beneficiary);
    });
  }

  updateForm(beneficiary: IBeneficiary): void {
    this.editForm.patchValue({
      id: beneficiary.id,
      name: beneficiary.name,
      type: beneficiary.type,
      address: beneficiary.address,
      phoneNumber: beneficiary.phoneNumber,
      email: beneficiary.email,
      contactPerson: beneficiary.contactPerson,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const beneficiary = this.createFromForm();
    if (beneficiary.id !== undefined) {
      this.subscribeToSaveResponse(this.beneficiaryService.update(beneficiary));
    } else {
      this.subscribeToSaveResponse(this.beneficiaryService.create(beneficiary));
    }
  }

  private createFromForm(): IBeneficiary {
    return {
      ...new Beneficiary(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      type: this.editForm.get(['type'])!.value,
      address: this.editForm.get(['address'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      email: this.editForm.get(['email'])!.value,
      contactPerson: this.editForm.get(['contactPerson'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBeneficiary>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
