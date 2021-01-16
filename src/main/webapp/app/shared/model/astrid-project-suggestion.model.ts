export interface IAstridProjectSuggestion {
  id?: number;
  name?: string;
  shortDescription?: any;
  documentationContentType?: string;
  documentation?: any;
  goal?: string;
  documentation1ContentType?: string;
  documentation1?: any;
  documentation2ContentType?: string;
  documentation2?: any;
  documentation3ContentType?: string;
  documentation3?: any;
  documentation4ContentType?: string;
  documentation4?: any;
  documentation5ContentType?: string;
  documentation5?: any;
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
    public documentation1ContentType?: string,
    public documentation1?: any,
    public documentation2ContentType?: string,
    public documentation2?: any,
    public documentation3ContentType?: string,
    public documentation3?: any,
    public documentation4ContentType?: string,
    public documentation4?: any,
    public documentation5ContentType?: string,
    public documentation5?: any,
    public initiatorLogin?: string,
    public initiatorId?: number,
    public statusName?: string,
    public statusId?: number
  ) {}
}
