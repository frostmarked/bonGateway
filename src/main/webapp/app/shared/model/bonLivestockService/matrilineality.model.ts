import { UserRole } from 'app/shared/model/enumerations/user-role.model';

export interface IMatrilineality {
  id?: number;
  familyname?: string;
  earTagId?: number;
  name?: string;
  country?: string;
  description?: string;
  cattleNameRegexPattern?: string;
  patriId?: number;
  patriName?: string;
  patriCountry?: string;
  polled?: boolean;
  storyHandle?: string;
  visibility?: UserRole;
}

export class Matrilineality implements IMatrilineality {
  constructor(
    public id?: number,
    public familyname?: string,
    public earTagId?: number,
    public name?: string,
    public country?: string,
    public description?: string,
    public cattleNameRegexPattern?: string,
    public patriId?: number,
    public patriName?: string,
    public patriCountry?: string,
    public polled?: boolean,
    public storyHandle?: string,
    public visibility?: UserRole
  ) {
    this.polled = this.polled || false;
  }
}
