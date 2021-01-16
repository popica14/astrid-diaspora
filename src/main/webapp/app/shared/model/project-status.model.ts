export interface IProjectStatus {
  id?: number;
  name?: string;
  daysToNotification?: number;
  order?: number;
}

export class ProjectStatus implements IProjectStatus {
  constructor(public id?: number, public name?: string, public daysToNotification?: number, public order?: number) {}
}
