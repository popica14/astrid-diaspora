export interface IProjectStatus {
  id?: number;
  name?: string;
}

export class ProjectStatus implements IProjectStatus {
  constructor(public id?: number, public name?: string) {}
}
