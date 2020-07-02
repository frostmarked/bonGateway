export interface IPasture {
  id?: number;
  name?: string;
  description?: string;
}

export class Pasture implements IPasture {
  constructor(public id?: number, public name?: string, public description?: string) {}
}
