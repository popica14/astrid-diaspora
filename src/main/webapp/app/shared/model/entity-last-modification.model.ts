import { Moment } from 'moment';

export interface IEntityLastModification {
  id?: number;
  lastModified?: Moment;
  lastModifiedByLogin?: string;
  lastModifiedById?: number;
  astridProjectId?: number;
}

export class EntityLastModification implements IEntityLastModification {
  constructor(
    public id?: number,
    public lastModified?: Moment,
    public lastModifiedByLogin?: string,
    public lastModifiedById?: number,
    public astridProjectId?: number
  ) {}
}
