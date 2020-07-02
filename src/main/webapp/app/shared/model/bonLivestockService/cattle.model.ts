import { IPhoto } from 'app/shared/model/bonLivestockService/photo.model';
import { INote } from 'app/shared/model/bonLivestockService/note.model';
import { IMatrilineality } from 'app/shared/model/bonLivestockService/matrilineality.model';
import { UserRole } from 'app/shared/model/enumerations/user-role.model';

export interface ICattle {
  id?: number;
  earTagId?: number;
  name?: string;
  visibility?: UserRole;
  upForSale?: boolean;
  showBlup?: boolean;
  alert?: boolean;
  storyHandle?: string;
  photos?: IPhoto[];
  notes?: INote[];
  matrilineality?: IMatrilineality;
}

export class Cattle implements ICattle {
  constructor(
    public id?: number,
    public earTagId?: number,
    public name?: string,
    public visibility?: UserRole,
    public upForSale?: boolean,
    public showBlup?: boolean,
    public alert?: boolean,
    public storyHandle?: string,
    public photos?: IPhoto[],
    public notes?: INote[],
    public matrilineality?: IMatrilineality
  ) {
    this.upForSale = this.upForSale || false;
    this.showBlup = this.showBlup || false;
    this.alert = this.alert || false;
  }
}
