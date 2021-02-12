import { Education } from 'app/shared/model/enumerations/education.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IUserExtended {
  id?: any;
  login?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  phoneNumber?: string;
  activated?: boolean;
  langKey?: string;
  authorities?: string[];
  createdBy?: string;
  createdDate?: Date;
  lastModifiedBy?: string;
  lastModifiedDate?: Date;
  password?: string;
  residency?: string;
  birthDate?: Date;
  highestEducation?: Education;
  gender?: Gender;
}

export class UserExtended implements IUserExtended {
  constructor(
    public id?: any,
    public login?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public phoneNumber?: string,
    public activated?: boolean,
    public langKey?: string,
    public authorities?: string[],
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public password?: string,
    public residency?: string,
    public birthDate?: Date,
    public highestEducation?: Education,
    public gender?: Gender
  ) {}
}
