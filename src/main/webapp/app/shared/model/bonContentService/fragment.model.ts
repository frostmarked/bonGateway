import { ILocalized } from 'app/shared/model/bonContentService/localized.model';
import { ITag } from 'app/shared/model/bonContentService/tag.model';
import { IStory } from 'app/shared/model/bonContentService/story.model';
import { FragmentTemplate } from 'app/shared/model/enumerations/fragment-template.model';
import { UserRole } from 'app/shared/model/enumerations/user-role.model';

export interface IFragment {
  id?: number;
  template?: FragmentTemplate;
  name?: string;
  title?: string;
  ingress?: string;
  body?: any;
  imageContentType?: string;
  image?: any;
  caption?: string;
  width?: number;
  height?: number;
  orderNo?: number;
  visibility?: UserRole;
  localizedFragments?: ILocalized[];
  tags?: ITag[];
  story?: IStory;
}

export class Fragment implements IFragment {
  constructor(
    public id?: number,
    public template?: FragmentTemplate,
    public name?: string,
    public title?: string,
    public ingress?: string,
    public body?: any,
    public imageContentType?: string,
    public image?: any,
    public caption?: string,
    public width?: number,
    public height?: number,
    public orderNo?: number,
    public visibility?: UserRole,
    public localizedFragments?: ILocalized[],
    public tags?: ITag[],
    public story?: IStory
  ) {}
}
