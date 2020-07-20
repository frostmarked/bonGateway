import { Moment } from 'moment';
import { IBovine } from 'app/shared/model/bonReplicaService/bovine.model';
import { EntryStatus } from 'app/shared/model/enumerations/entry-status.model';

export interface IJournalEntry {
  id?: number;
  status?: EntryStatus;
  date?: Moment;
  edited?: Moment;
  herdId?: number;
  newHerdId?: number;
  subState1?: number;
  subState2?: number;
  bovine?: IBovine;
}

export class JournalEntry implements IJournalEntry {
  constructor(
    public id?: number,
    public status?: EntryStatus,
    public date?: Moment,
    public edited?: Moment,
    public herdId?: number,
    public newHerdId?: number,
    public subState1?: number,
    public subState2?: number,
    public bovine?: IBovine
  ) {}
}
