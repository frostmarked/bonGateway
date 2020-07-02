import { IBovine } from 'app/shared/model/bonReplicaService/bovine.model';

export interface IBlup {
  id?: number;
  t0?: number;
  d0?: number;
  m0?: number;
  t200?: number;
  d200?: number;
  m200?: number;
  t365?: number;
  d365?: number;
  total?: number;
  status?: string;
  bovine?: IBovine;
}

export class Blup implements IBlup {
  constructor(
    public id?: number,
    public t0?: number,
    public d0?: number,
    public m0?: number,
    public t200?: number,
    public d200?: number,
    public m200?: number,
    public t365?: number,
    public d365?: number,
    public total?: number,
    public status?: string,
    public bovine?: IBovine
  ) {}
}
