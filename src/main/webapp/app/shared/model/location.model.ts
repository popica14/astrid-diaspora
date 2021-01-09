export interface ILocation {
  id?: number;
  name?: string;
  county?: string;
  address?: string;
}

export class Location implements ILocation {
  constructor(public id?: number, public name?: string, public county?: string, public address?: string) {}
}
