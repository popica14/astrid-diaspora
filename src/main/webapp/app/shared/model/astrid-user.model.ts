import { Moment } from 'moment';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { Education } from 'app/shared/model/enumerations/education.model';

export interface IAstridUser {
  id?: number;
  phoneNumber?: string;
  residency?: string;
  gender?: Gender;
  birthDate?: Moment;
  highestEducation?: Education;
  userId?: number;
}

export class AstridUser implements IAstridUser {
  constructor(
    public id?: number,
    public phoneNumber?: string,
    public residency?: string,
    public gender?: Gender,
    public birthDate?: Moment,
    public highestEducation?: Education,
    public userId?: number
  ) {}
}
