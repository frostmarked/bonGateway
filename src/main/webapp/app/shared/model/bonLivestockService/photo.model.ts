import { Moment } from 'moment';
import { ICattle } from 'app/shared/model/bonLivestockService/cattle.model';
import { UserRole } from 'app/shared/model/enumerations/user-role.model';

export interface IPhoto {
  id?: number;
  imageContentType?: string;
  image?: any;
  caption?: string;
  height?: number;
  width?: number;
  taken?: Moment;
  visibility?: UserRole;
  cattle?: ICattle;
}

export class Photo implements IPhoto {
  constructor(
    public id?: number,
    public imageContentType?: string,
    public image?: any,
    public caption?: string,
    public height?: number,
    public width?: number,
    public taken?: Moment,
    public visibility?: UserRole,
    public cattle?: ICattle
  ) {}
}
