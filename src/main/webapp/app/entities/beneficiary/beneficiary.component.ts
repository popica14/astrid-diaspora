import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBeneficiary } from 'app/shared/model/beneficiary.model';
import { BeneficiaryService } from './beneficiary.service';
import { BeneficiaryDeleteDialogComponent } from './beneficiary-delete-dialog.component';

@Component({
  selector: 'jhi-beneficiary',
  templateUrl: './beneficiary.component.html',
})
export class BeneficiaryComponent implements OnInit, OnDestroy {
  beneficiaries?: IBeneficiary[];
  eventSubscriber?: Subscription;

  constructor(
    protected beneficiaryService: BeneficiaryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.beneficiaryService.query().subscribe((res: HttpResponse<IBeneficiary[]>) => (this.beneficiaries = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBeneficiaries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBeneficiary): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBeneficiaries(): void {
    this.eventSubscriber = this.eventManager.subscribe('beneficiaryListModification', () => this.loadAll());
  }

  delete(beneficiary: IBeneficiary): void {
    const modalRef = this.modalService.open(BeneficiaryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.beneficiary = beneficiary;
  }
}
