export interface IProjectStatus {
  id?: number;
  name?: string;
  daysToNotification?: number;
}

export class ProjectStatus implements IProjectStatus {
  constructor(public id?: number, public name?: string, public daysToNotification?: number) {}
}
