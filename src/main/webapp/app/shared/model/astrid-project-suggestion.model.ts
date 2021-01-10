export interface IAstridProjectSuggestion {
  id?: number;
  name?: string;
  shortDescription?: any;
  documentationContentType?: string;
  documentation?: any;
  goal?: string;
  initiatorLogin?: string;
  initiatorId?: number;
  statusName?: string;
  statusId?: number;
}

export class AstridProjectSuggestion implements IAstridProjectSuggestion {
  constructor(
    public id?: number,
    public name?: string,
    public shortDescription?: any,
    public documentationContentType?: string,
    public documentation?: any,
    public goal?: string,
    public initiatorLogin?: string,
    public initiatorId?: number,
    public statusName?: string,
    public statusId?: number
  ) {}
}
