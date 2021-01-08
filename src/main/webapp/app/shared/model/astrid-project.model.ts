import { IUser } from 'app/core/user/user.model';

export interface IAstridProject {
  id?: number;
  name?: string;
  description?: any;
  responsibleLogin?: string;
  responsibleId?: number;
  implementationTeams?: IUser[];
}

export class AstridProject implements IAstridProject {
  constructor(
    public id?: number,
    public name?: string,
    public description?: any,
    public responsibleLogin?: string,
    public responsibleId?: number,
    public implementationTeams?: IUser[]
  ) {}
}
