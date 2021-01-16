export interface IAstridUser {
  id?: number;
  phoneNumber?: string;
  userId?: number;
}

export class AstridUser implements IAstridUser {
  constructor(public id?: number, public phoneNumber?: string, public userId?: number) {}
}
