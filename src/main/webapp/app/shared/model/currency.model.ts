export interface ICurrency {
  id?: number;
  name?: string;
  symbol?: string;
}

export class Currency implements ICurrency {
  constructor(public id?: number, public name?: string, public symbol?: string) {}
}
