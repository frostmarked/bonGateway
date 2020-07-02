import { Moment } from 'moment';
import { IJournalEntry } from 'app/shared/model/bonReplicaService/journal-entry.model';
import { ISourceFile } from 'app/shared/model/bonReplicaService/source-file.model';
import { IBlup } from 'app/shared/model/bonReplicaService/blup.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';
import { BovineStatus } from 'app/shared/model/enumerations/bovine-status.model';
import { HornStatus } from 'app/shared/model/enumerations/horn-status.model';

export interface IBovine {
  id?: number;
  earTagId?: number;
  masterIdentifier?: string;
  country?: string;
  herdId?: number;
  birthDate?: Moment;
  gender?: Gender;
  name?: string;
  bovineStatus?: BovineStatus;
  hornStatus?: HornStatus;
  matriId?: number;
  patriId?: number;
  weight0?: number;
  weight200?: number;
  weight365?: number;
  journalEntries?: IJournalEntry[];
  sourceFile?: ISourceFile;
  blup?: IBlup;
}

export class Bovine implements IBovine {
  constructor(
    public id?: number,
    public earTagId?: number,
    public masterIdentifier?: string,
    public country?: string,
    public herdId?: number,
    public birthDate?: Moment,
    public gender?: Gender,
    public name?: string,
    public bovineStatus?: BovineStatus,
    public hornStatus?: HornStatus,
    public matriId?: number,
    public patriId?: number,
    public weight0?: number,
    public weight200?: number,
    public weight365?: number,
    public journalEntries?: IJournalEntry[],
    public sourceFile?: ISourceFile,
    public blup?: IBlup
  ) {}
}
