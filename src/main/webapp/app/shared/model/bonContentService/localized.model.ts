import { IFragment } from 'app/shared/model/bonContentService/fragment.model';
import { UserRole } from 'app/shared/model/enumerations/user-role.model';

export interface ILocalized {
  id?: number;
  i18n?: string;
  title?: string;
  ingress?: string;
  body?: any;
  caption?: string;
  visibility?: UserRole;
  fragment?: IFragment;
}

export class Localized implements ILocalized {
  constructor(
    public id?: number,
    public i18n?: string,
    public title?: string,
    public ingress?: string,
    public body?: any,
    public caption?: string,
    public visibility?: UserRole,
    public fragment?: IFragment
  ) {}
}
