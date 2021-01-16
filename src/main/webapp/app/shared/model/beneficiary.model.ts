import { IAstridProject } from 'app/shared/model/astrid-project.model';
import { BeneficiaryType } from 'app/shared/model/enumerations/beneficiary-type.model';

export interface IBeneficiary {
  id?: number;
  name?: string;
  type?: BeneficiaryType;
  address?: string;
  phoneNumber?: string;
  email?: string;
  contactPerson?: string;
  projects?: IAstridProject[];
}

export class Beneficiary implements IBeneficiary {
  constructor(
    public id?: number,
    public name?: string,
    public type?: BeneficiaryType,
    public address?: string,
    public phoneNumber?: string,
    public email?: string,
    public contactPerson?: string,
    public projects?: IAstridProject[]
  ) {}
}
