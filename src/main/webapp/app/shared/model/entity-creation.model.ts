import { Moment } from 'moment';

export interface IEntityCreation {
  id?: number;
  created?: Moment;
  createdByLogin?: string;
  createdById?: number;
  astridProjectId?: number;
}

export class EntityCreation implements IEntityCreation {
  constructor(
    public id?: number,
    public created?: Moment,
    public createdByLogin?: string,
    public createdById?: number,
    public astridProjectId?: number
  ) {}
}
