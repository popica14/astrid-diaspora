import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { IBeneficiary } from 'app/shared/model/beneficiary.model';

export interface IAstridProject {
  id?: number;
  name?: string;
  shortDescription?: any;
  documentationContentType?: string;
  documentation?: any;
  neededAmount?: string;
  currentAmount?: string;
  currency?: string;
  supporters?: number;
  goal?: string;
  statusReason?: string;
  statusDeadline?: Moment;
  entityCreationId?: number;
  entityLastModificationId?: number;
  responsibleLogin?: string;
  responsibleId?: number;
  statusName?: string;
  statusId?: number;
  locationName?: string;
  locationId?: number;
  implementationTeams?: IUser[];
  beneficiaries?: IBeneficiary[];
}

export class AstridProject implements IAstridProject {
  constructor(
    public id?: number,
    public name?: string,
    public shortDescription?: any,
    public documentationContentType?: string,
    public documentation?: any,
    public neededAmount?: string,
    public currentAmount?: string,
    public currency?: string,
    public supporters?: number,
    public goal?: string,
    public statusReason?: string,
    public statusDeadline?: Moment,
    public entityCreationId?: number,
    public entityLastModificationId?: number,
    public responsibleLogin?: string,
    public responsibleId?: number,
    public statusName?: string,
    public statusId?: number,
    public locationName?: string,
    public locationId?: number,
    public implementationTeams?: IUser[],
    public beneficiaries?: IBeneficiary[]
  ) {}
}
