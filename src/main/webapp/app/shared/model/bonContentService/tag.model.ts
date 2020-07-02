import { IFragment } from 'app/shared/model/bonContentService/fragment.model';

export interface ITag {
  id?: number;
  name?: string;
  fragments?: IFragment[];
}

export class Tag implements ITag {
  constructor(public id?: number, public name?: string, public fragments?: IFragment[]) {}
}
