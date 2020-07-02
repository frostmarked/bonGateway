import { Moment } from 'moment';
import { IPasture } from 'app/shared/model/bonLivestockService/pasture.model';
import { ICattle } from 'app/shared/model/bonLivestockService/cattle.model';
import { NoteCategory } from 'app/shared/model/enumerations/note-category.model';

export interface INote {
  id?: number;
  category?: NoteCategory;
  note?: string;
  actualDate?: Moment;
  pasture?: IPasture;
  cattle?: ICattle;
}

export class Note implements INote {
  constructor(
    public id?: number,
    public category?: NoteCategory,
    public note?: string,
    public actualDate?: Moment,
    public pasture?: IPasture,
    public cattle?: ICattle
  ) {}
}
