import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { IBeneficiary } from 'app/shared/model/beneficiary.model';

export interface IAstridProject {
  id?: number;
  name?: string;
  shortDescription?: any;
  neededAmount?: string;
  currentAmount?: string;
  supporters?: number;
  goal?: string;
  statusReason?: string;
  statusDeadline?: Moment;
  documentation1ContentType?: string;
  documentation1?: any;
  documentation2ContentType?: string;
  documentation2?: any;
  documentation3ContentType?: string;
  documentation3?: any;
  documentation4ContentType?: string;
  documentation4?: any;
  documentation5ContentType?: string;
  documentation5?: any;
  entityCreationId?: number;
  entityLastModificationId?: number;
  responsibleLogin?: string;
  responsibleId?: number;
  initiatorLogin?: string;
  initiatorId?: number;
  statusName?: string;
  statusId?: number;
  locationName?: string;
  locationId?: number;
  currencyName?: string;
  currencyId?: number;
  implementationTeams?: IUser[];
  beneficiaries?: IBeneficiary[];
}

export class AstridProject implements IAstridProject {
  constructor(
    public id?: number,
    public name?: string,
    public shortDescription?: any,
    public neededAmount?: string,
    public currentAmount?: string,
    public supporters?: number,
    public goal?: string,
    public statusReason?: string,
    public statusDeadline?: Moment,
    public documentation1ContentType?: string,
    public documentation1?: any,
    public documentation2ContentType?: string,
    public documentation2?: any,
    public documentation3ContentType?: string,
    public documentation3?: any,
    public documentation4ContentType?: string,
    public documentation4?: any,
    public documentation5ContentType?: string,
    public documentation5?: any,
    public entityCreationId?: number,
    public entityLastModificationId?: number,
    public responsibleLogin?: string,
    public responsibleId?: number,
    public initiatorLogin?: string,
    public initiatorId?: number,
    public statusName?: string,
    public statusId?: number,
    public locationName?: string,
    public locationId?: number,
    public currencyName?: string,
    public currencyId?: number,
    public implementationTeams?: IUser[],
    public beneficiaries?: IBeneficiary[]
  ) {}
}
