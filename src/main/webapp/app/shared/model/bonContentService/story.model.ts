import { IFragment } from 'app/shared/model/bonContentService/fragment.model';
import { StoryCategory } from 'app/shared/model/enumerations/story-category.model';
import { UserRole } from 'app/shared/model/enumerations/user-role.model';

export interface IStory {
  id?: number;
  category?: StoryCategory;
  name?: string;
  visibility?: UserRole;
  fragments?: IFragment[];
}

export class Story implements IStory {
  constructor(
    public id?: number,
    public category?: StoryCategory,
    public name?: string,
    public visibility?: UserRole,
    public fragments?: IFragment[]
  ) {}
}
