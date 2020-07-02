import { Moment } from 'moment';

export interface ISourceFile {
  id?: number;
  name?: string;
  zipFileContentType?: string;
  zipFile?: any;
  processed?: Moment;
  outcome?: string;
}

export class SourceFile implements ISourceFile {
  constructor(
    public id?: number,
    public name?: string,
    public zipFileContentType?: string,
    public zipFile?: any,
    public processed?: Moment,
    public outcome?: string
  ) {}
}
