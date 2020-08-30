import gql from 'graphql-tag';
import { Injectable } from '@angular/core';
import * as Apollo from 'apollo-angular';
export type Maybe<T> = T | null;
export type Exact<T extends { [key: string]: any }> = { [K in keyof T]: T[K] };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
};



export type ArticleVo = {
  __typename?: 'ArticleVO';
  category?: Maybe<Category>;
  id?: Maybe<Scalars['Float']>;
  name?: Maybe<Scalars['String']>;
  sections?: Maybe<Array<Maybe<SectionVo>>>;
  visibility?: Maybe<Visibility>;
};

export type BlupVo = {
  __typename?: 'BlupVO';
  d0?: Maybe<Scalars['Int']>;
  d200?: Maybe<Scalars['Int']>;
  d365?: Maybe<Scalars['Int']>;
  m0?: Maybe<Scalars['Int']>;
  m200?: Maybe<Scalars['Int']>;
  status?: Maybe<Scalars['String']>;
  t0?: Maybe<Scalars['Int']>;
  t200?: Maybe<Scalars['Int']>;
  t365?: Maybe<Scalars['Int']>;
  total?: Maybe<Scalars['Int']>;
};

export enum Category {
  News = 'NEWS',
  It = 'IT',
  Matrilineality = 'MATRILINEALITY',
  Cattle = 'CATTLE'
}

export enum CategoryInListItem {
  News = 'NEWS',
  It = 'IT',
  Matrilineality = 'MATRILINEALITY',
  Cattle = 'CATTLE'
}

export type CowVo = {
  __typename?: 'CowVO';
  birthDate?: Maybe<Scalars['String']>;
  earTagId?: Maybe<Scalars['Int']>;
  gender?: Maybe<Gender>;
  hornStatus?: Maybe<HornStatus>;
  linageId?: Maybe<Scalars['Float']>;
  linageName?: Maybe<Scalars['String']>;
  matriId?: Maybe<Scalars['Int']>;
  name?: Maybe<Scalars['String']>;
  patriId?: Maybe<Scalars['Int']>;
  storyHandle?: Maybe<Scalars['String']>;
  visibility?: Maybe<Visibility>;
  weight0?: Maybe<Scalars['Int']>;
  weight200?: Maybe<Scalars['Int']>;
  weight365?: Maybe<Scalars['Int']>;
};

export enum Gender {
  Heifer = 'HEIFER',
  Bull = 'BULL'
}

export enum GenderEquals {
  Heifer = 'HEIFER',
  Bull = 'BULL'
}

export enum HornStatus {
  Unknown = 'UNKNOWN',
  Horned = 'HORNED',
  Polled = 'POLLED',
  Scurs = 'SCURS',
  Dehorned = 'DEHORNED',
  Disbudded = 'DISBUDDED'
}

export enum HornStatusInListItem {
  Unknown = 'UNKNOWN',
  Horned = 'HORNED',
  Polled = 'POLLED',
  Scurs = 'SCURS',
  Dehorned = 'DEHORNED',
  Disbudded = 'DISBUDDED'
}

export enum I18n {
  Sv = 'sv',
  En = 'en'
}

export type LinageVo = {
  __typename?: 'LinageVO';
  country?: Maybe<Scalars['String']>;
  earTagId?: Maybe<Scalars['Int']>;
  familyname?: Maybe<Scalars['String']>;
  id?: Maybe<Scalars['Float']>;
  name?: Maybe<Scalars['String']>;
  patriCountry?: Maybe<Scalars['String']>;
  patriId?: Maybe<Scalars['Int']>;
  patriName?: Maybe<Scalars['String']>;
  polled?: Maybe<Scalars['Boolean']>;
  storyHandle?: Maybe<Scalars['String']>;
  visibility?: Maybe<Visibility>;
};

export type PictureSourceVo = {
  __typename?: 'PictureSourceVO';
  contentType?: Maybe<Scalars['String']>;
  height?: Maybe<Scalars['Int']>;
  name?: Maybe<Scalars['String']>;
  url?: Maybe<Scalars['String']>;
  width?: Maybe<Scalars['Int']>;
};

export type PictureVo = {
  __typename?: 'PictureVO';
  caption?: Maybe<Scalars['String']>;
  id?: Maybe<Scalars['Float']>;
  sources?: Maybe<Array<Maybe<PictureSourceVo>>>;
  taken?: Maybe<Scalars['String']>;
  visibility?: Maybe<Visibility>;
};

/** The start of any query */
export type Query = {
  __typename?: 'Query';
  /**
   * Get all ArticleVOs
   * 
   * Equivalent to GET /api/public/articles
   */
  apiPublicArticles?: Maybe<Array<Maybe<ArticleVo>>>;
  /**
   * Find all cows matching criteria
   * 
   * Equivalent to GET /api/public/cows
   */
  apiPublicCows?: Maybe<Array<Maybe<CowVo>>>;
  /**
   * Get all PictureVOs for a cow
   * 
   * Equivalent to GET /api/public/cows/{earTagId}/pictures
   */
  apiPublicCowsPictures?: Maybe<Array<Maybe<PictureVo>>>;
  /**
   * Get image for a cow
   * 
   * Equivalent to GET /api/public/cows/{earTagId}/pictures/{pictureId}/{name}
   */
  apiPublicCowsPictures2?: Maybe<Scalars['String']>;
  /**
   * Find all LinageVOs
   * 
   * Equivalent to GET /api/public/linages
   */
  apiPublicLinages?: Maybe<Array<Maybe<LinageVo>>>;
  /**
   * Search ArticleVOs
   * 
   * Equivalent to GET /api/public/_search/articles
   */
  apiPublicSearchArticles?: Maybe<Array<Maybe<ArticleVo>>>;
  /**
   * Get all TagVOs
   * 
   * Equivalent to GET /api/public/tags
   */
  apiPublicTags?: Maybe<Array<Maybe<TagVo>>>;
  /**
   * Get ArticleVO by Id or handle
   * 
   * Equivalent to GET /api/public/articles/{id}
   */
  articleVO?: Maybe<ArticleVo>;
  /**
   * Get BLUP for a cow
   * 
   * Equivalent to GET /api/public/cows/{earTagId}/blup
   */
  blupVO?: Maybe<BlupVo>;
  /**
   * getCowVO
   * 
   * Equivalent to GET /api/public/cows/{earTagId}
   */
  cowVO?: Maybe<CowVo>;
  /**
   * Get LinageVO
   * 
   * Equivalent to GET /api/public/linages/{earTagId}
   */
  linageVO?: Maybe<LinageVo>;
};


/** The start of any query */
export type QueryApiPublicArticlesArgs = {
  categoryIn?: Maybe<Array<Maybe<CategoryInListItem>>>;
  i18n: I18n;
  page?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
};


/** The start of any query */
export type QueryApiPublicCowsArgs = {
  birthDateGreaterThan?: Maybe<Scalars['String']>;
  birthDateLessThan?: Maybe<Scalars['String']>;
  genderEquals?: Maybe<GenderEquals>;
  hornStatusIn?: Maybe<Array<Maybe<HornStatusInListItem>>>;
  linageIdEquals?: Maybe<Scalars['Int']>;
  matriIdEquals?: Maybe<Scalars['Int']>;
  page?: Maybe<Scalars['Int']>;
  patriIdEquals?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
  weight0GreaterThan?: Maybe<Scalars['Int']>;
  weight0LessThan?: Maybe<Scalars['Int']>;
  weight0Specified?: Maybe<Scalars['Boolean']>;
  weight200GreaterThan?: Maybe<Scalars['Int']>;
  weight200LessThan?: Maybe<Scalars['Int']>;
  weight200Specified?: Maybe<Scalars['Boolean']>;
  weight365GreaterThan?: Maybe<Scalars['Int']>;
  weight365LessThan?: Maybe<Scalars['Int']>;
  weight365Specified?: Maybe<Scalars['Boolean']>;
};


/** The start of any query */
export type QueryApiPublicCowsPicturesArgs = {
  earTagId: Scalars['Float'];
  page?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
};


/** The start of any query */
export type QueryApiPublicCowsPictures2Args = {
  earTagId: Scalars['Float'];
  name: Scalars['String'];
  pictureId: Scalars['Float'];
};


/** The start of any query */
export type QueryApiPublicLinagesArgs = {
  page?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
};


/** The start of any query */
export type QueryApiPublicSearchArticlesArgs = {
  categoryIn?: Maybe<Array<Maybe<CategoryInListItem>>>;
  i18n: I18n;
  page?: Maybe<Scalars['Int']>;
  query: Scalars['String'];
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
};


/** The start of any query */
export type QueryApiPublicTagsArgs = {
  page?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
};


/** The start of any query */
export type QueryArticleVoArgs = {
  i18n: I18n;
  id: Scalars['String'];
  isHandle?: Maybe<Scalars['Boolean']>;
};


/** The start of any query */
export type QueryBlupVoArgs = {
  earTagId: Scalars['Float'];
};


/** The start of any query */
export type QueryCowVoArgs = {
  earTagId: Scalars['Float'];
};


/** The start of any query */
export type QueryLinageVoArgs = {
  earTagId: Scalars['Float'];
};

export type SectionVo = {
  __typename?: 'SectionVO';
  body?: Maybe<Scalars['String']>;
  caption?: Maybe<Scalars['String']>;
  height?: Maybe<Scalars['Int']>;
  id?: Maybe<Scalars['Float']>;
  image?: Maybe<Scalars['String']>;
  imageContentType?: Maybe<Scalars['String']>;
  ingress?: Maybe<Scalars['String']>;
  orderNo?: Maybe<Scalars['Int']>;
  tags?: Maybe<Array<Maybe<TagVo>>>;
  template?: Maybe<Template>;
  title?: Maybe<Scalars['String']>;
  visibility?: Maybe<Visibility>;
  width?: Maybe<Scalars['Int']>;
};

export type TagVo = {
  __typename?: 'TagVO';
  id?: Maybe<Scalars['Float']>;
  name?: Maybe<Scalars['String']>;
};

export enum Template {
  V1 = 'V1',
  V2 = 'V2',
  V3 = 'V3'
}

export enum Visibility {
  RoleAdmin = 'ROLE_ADMIN',
  RoleUser = 'ROLE_USER',
  RoleAnonymous = 'ROLE_ANONYMOUS'
}

export type GetArticleQueryVariables = Exact<{
  i18n: I18n;
  id: Scalars['String'];
  isHandle?: Maybe<Scalars['Boolean']>;
  isSummary?: Maybe<Scalars['Boolean']>;
}>;


export type GetArticleQuery = (
  { __typename?: 'Query' }
  & { articleVO?: Maybe<(
    { __typename?: 'ArticleVO' }
    & Pick<ArticleVo, 'id' | 'category' | 'name' | 'visibility'>
    & { sections?: Maybe<Array<Maybe<(
      { __typename?: 'SectionVO' }
      & Pick<SectionVo, 'id' | 'body' | 'ingress' | 'orderNo' | 'template' | 'title' | 'visibility'>
      & { tags?: Maybe<Array<Maybe<(
        { __typename?: 'TagVO' }
        & Pick<TagVo, 'name'>
      )>>> }
      & SectionImageFragmentFragment
    )>>> }
  )> }
);

export type GetCowQueryVariables = Exact<{
  earTagId: Scalars['Float'];
}>;


export type GetCowQuery = (
  { __typename?: 'Query' }
  & { cowVO?: Maybe<(
    { __typename?: 'CowVO' }
    & Pick<CowVo, 'birthDate' | 'earTagId' | 'gender' | 'hornStatus' | 'linageId' | 'linageName' | 'matriId' | 'name' | 'patriId' | 'storyHandle' | 'visibility' | 'weight0' | 'weight200' | 'weight365'>
  )> }
);

export type FindArticlesQueryVariables = Exact<{
  i18n: I18n;
  categoryIn?: Maybe<Array<Maybe<CategoryInListItem>>>;
  page?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
  isSummary?: Maybe<Scalars['Boolean']>;
}>;


export type FindArticlesQuery = (
  { __typename?: 'Query' }
  & { apiPublicArticles?: Maybe<Array<Maybe<(
    { __typename?: 'ArticleVO' }
    & Pick<ArticleVo, 'id' | 'category' | 'name' | 'visibility'>
    & { sections?: Maybe<Array<Maybe<(
      { __typename?: 'SectionVO' }
      & Pick<SectionVo, 'id' | 'title' | 'body' | 'ingress' | 'orderNo' | 'template' | 'visibility'>
      & { tags?: Maybe<Array<Maybe<(
        { __typename?: 'TagVO' }
        & Pick<TagVo, 'name'>
      )>>> }
      & SectionImageFragmentFragment
    )>>> }
  )>>> }
);

export type FindCowPicturesQueryVariables = Exact<{
  earTagId: Scalars['Float'];
  page?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
}>;


export type FindCowPicturesQuery = (
  { __typename?: 'Query' }
  & { apiPublicCowsPictures?: Maybe<Array<Maybe<(
    { __typename?: 'PictureVO' }
    & Pick<PictureVo, 'id' | 'caption' | 'taken' | 'visibility'>
    & { sources?: Maybe<Array<Maybe<(
      { __typename?: 'PictureSourceVO' }
      & Pick<PictureSourceVo, 'name' | 'contentType' | 'height' | 'width' | 'url'>
    )>>> }
  )>>> }
);

export type FindCowsQueryVariables = Exact<{
  page?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
  birthDateGreaterThan?: Maybe<Scalars['String']>;
  birthDateLessThan?: Maybe<Scalars['String']>;
  genderEquals?: Maybe<GenderEquals>;
  hornStatusIn?: Maybe<Array<Maybe<HornStatusInListItem>>>;
  linageIdEquals?: Maybe<Scalars['Int']>;
  matriIdEquals?: Maybe<Scalars['Int']>;
  patriIdEquals?: Maybe<Scalars['Int']>;
  weight0GreaterThan?: Maybe<Scalars['Int']>;
  weight0LessThan?: Maybe<Scalars['Int']>;
  weight0Specified?: Maybe<Scalars['Boolean']>;
  weight200GreaterThan?: Maybe<Scalars['Int']>;
  weight200LessThan?: Maybe<Scalars['Int']>;
  weight200Specified?: Maybe<Scalars['Boolean']>;
  weight365GreaterThan?: Maybe<Scalars['Int']>;
  weight365LessThan?: Maybe<Scalars['Int']>;
  weight365Specified?: Maybe<Scalars['Boolean']>;
}>;


export type FindCowsQuery = (
  { __typename?: 'Query' }
  & { apiPublicCows?: Maybe<Array<Maybe<(
    { __typename?: 'CowVO' }
    & Pick<CowVo, 'birthDate' | 'earTagId' | 'gender' | 'hornStatus' | 'linageId' | 'linageName' | 'matriId' | 'name' | 'patriId' | 'storyHandle' | 'visibility' | 'weight0' | 'weight200' | 'weight365'>
  )>>> }
);

export type FindLinagesQueryVariables = Exact<{
  page?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
}>;


export type FindLinagesQuery = (
  { __typename?: 'Query' }
  & { apiPublicLinages?: Maybe<Array<Maybe<(
    { __typename?: 'LinageVO' }
    & Pick<LinageVo, 'id' | 'visibility' | 'name' | 'earTagId' | 'familyname' | 'polled' | 'country'>
  )>>> }
);

export type FindTagsQueryVariables = Exact<{
  page?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
}>;


export type FindTagsQuery = (
  { __typename?: 'Query' }
  & { apiPublicTags?: Maybe<Array<Maybe<(
    { __typename?: 'TagVO' }
    & Pick<TagVo, 'id' | 'name'>
  )>>> }
);

export type GetLinageQueryVariables = Exact<{
  earTagId: Scalars['Float'];
}>;


export type GetLinageQuery = (
  { __typename?: 'Query' }
  & { linageVO?: Maybe<(
    { __typename?: 'LinageVO' }
    & Pick<LinageVo, 'id' | 'visibility' | 'name' | 'earTagId' | 'familyname' | 'polled' | 'country' | 'storyHandle' | 'patriId' | 'patriName' | 'patriCountry'>
  )> }
);

export type SearchArticlesQueryVariables = Exact<{
  i18n: I18n;
  query: Scalars['String'];
  categoryIn?: Maybe<Array<Maybe<CategoryInListItem>>>;
  page?: Maybe<Scalars['Int']>;
  size?: Maybe<Scalars['Int']>;
  sort?: Maybe<Array<Maybe<Scalars['String']>>>;
  isSummary?: Maybe<Scalars['Boolean']>;
}>;


export type SearchArticlesQuery = (
  { __typename?: 'Query' }
  & { apiPublicSearchArticles?: Maybe<Array<Maybe<(
    { __typename?: 'ArticleVO' }
    & Pick<ArticleVo, 'id' | 'category' | 'name' | 'visibility'>
    & { sections?: Maybe<Array<Maybe<(
      { __typename?: 'SectionVO' }
      & Pick<SectionVo, 'id' | 'title' | 'body' | 'ingress' | 'orderNo' | 'template' | 'visibility'>
      & { tags?: Maybe<Array<Maybe<(
        { __typename?: 'TagVO' }
        & Pick<TagVo, 'name'>
      )>>> }
      & SectionImageFragmentFragment
    )>>> }
  )>>> }
);

export type SectionImageFragmentFragment = (
  { __typename?: 'SectionVO' }
  & Pick<SectionVo, 'caption' | 'width' | 'height' | 'image' | 'imageContentType'>
);

export const SectionImageFragmentFragmentDoc = gql`
    fragment SectionImageFragment on SectionVO {
  caption @skip(if: $isSummary)
  width @skip(if: $isSummary)
  height @skip(if: $isSummary)
  image @skip(if: $isSummary)
  imageContentType @skip(if: $isSummary)
}
    `;
export const GetArticleDocument = gql`
    query GetArticle($i18n: I18n!, $id: String!, $isHandle: Boolean, $isSummary: Boolean = false) {
  articleVO(i18n: $i18n, id: $id, isHandle: $isHandle) {
    id
    category
    name
    visibility
    sections {
      id
      body
      ingress
      orderNo
      tags {
        name
      }
      template
      title
      visibility
      ...SectionImageFragment
    }
  }
}
    ${SectionImageFragmentFragmentDoc}`;

  @Injectable({
    providedIn: 'root'
  })
  export class GetArticleGQL extends Apollo.Query<GetArticleQuery, GetArticleQueryVariables> {
    document = GetArticleDocument;
    
  }
export const GetCowDocument = gql`
    query GetCow($earTagId: Float!) {
  cowVO(earTagId: $earTagId) {
    birthDate
    earTagId
    gender
    hornStatus
    linageId
    linageName
    matriId
    name
    patriId
    storyHandle
    visibility
    weight0
    weight200
    weight365
  }
}
    `;

  @Injectable({
    providedIn: 'root'
  })
  export class GetCowGQL extends Apollo.Query<GetCowQuery, GetCowQueryVariables> {
    document = GetCowDocument;
    
  }
export const FindArticlesDocument = gql`
    query FindArticles($i18n: I18n!, $categoryIn: [CategoryInListItem], $page: Int, $size: Int, $sort: [String], $isSummary: Boolean = true) {
  apiPublicArticles(categoryIn: $categoryIn, i18n: $i18n, page: $page, size: $size, sort: $sort) {
    id
    category
    name
    visibility
    sections {
      id
      title
      body @skip(if: $isSummary)
      ingress
      orderNo
      tags {
        name
      }
      template
      visibility
      ...SectionImageFragment
    }
  }
}
    ${SectionImageFragmentFragmentDoc}`;

  @Injectable({
    providedIn: 'root'
  })
  export class FindArticlesGQL extends Apollo.Query<FindArticlesQuery, FindArticlesQueryVariables> {
    document = FindArticlesDocument;
    
  }
export const FindCowPicturesDocument = gql`
    query FindCowPictures($earTagId: Float!, $page: Int, $size: Int, $sort: [String]) {
  apiPublicCowsPictures(earTagId: $earTagId, page: $page, size: $size, sort: $sort) {
    id
    caption
    taken
    visibility
    sources {
      name
      contentType
      height
      width
      url
    }
  }
}
    `;

  @Injectable({
    providedIn: 'root'
  })
  export class FindCowPicturesGQL extends Apollo.Query<FindCowPicturesQuery, FindCowPicturesQueryVariables> {
    document = FindCowPicturesDocument;
    
  }
export const FindCowsDocument = gql`
    query FindCows($page: Int, $size: Int, $sort: [String], $birthDateGreaterThan: String, $birthDateLessThan: String, $genderEquals: GenderEquals, $hornStatusIn: [HornStatusInListItem], $linageIdEquals: Int, $matriIdEquals: Int, $patriIdEquals: Int, $weight0GreaterThan: Int, $weight0LessThan: Int, $weight0Specified: Boolean, $weight200GreaterThan: Int, $weight200LessThan: Int, $weight200Specified: Boolean, $weight365GreaterThan: Int, $weight365LessThan: Int, $weight365Specified: Boolean) {
  apiPublicCows(birthDateGreaterThan: $birthDateGreaterThan, birthDateLessThan: $birthDateLessThan, genderEquals: $genderEquals, hornStatusIn: $hornStatusIn, linageIdEquals: $linageIdEquals, matriIdEquals: $matriIdEquals, page: $page, patriIdEquals: $patriIdEquals, size: $size, sort: $sort, weight0GreaterThan: $weight0GreaterThan, weight0LessThan: $weight0LessThan, weight0Specified: $weight0Specified, weight200GreaterThan: $weight200GreaterThan, weight200LessThan: $weight200LessThan, weight200Specified: $weight200Specified, weight365GreaterThan: $weight365GreaterThan, weight365LessThan: $weight365LessThan, weight365Specified: $weight365Specified) {
    birthDate
    earTagId
    gender
    hornStatus
    linageId
    linageName
    matriId
    name
    patriId
    storyHandle
    visibility
    weight0
    weight200
    weight365
  }
}
    `;

  @Injectable({
    providedIn: 'root'
  })
  export class FindCowsGQL extends Apollo.Query<FindCowsQuery, FindCowsQueryVariables> {
    document = FindCowsDocument;
    
  }
export const FindLinagesDocument = gql`
    query FindLinages($page: Int, $size: Int, $sort: [String]) {
  apiPublicLinages(page: $page, size: $size, sort: $sort) {
    id
    visibility
    name
    earTagId
    familyname
    polled
    country
  }
}
    `;

  @Injectable({
    providedIn: 'root'
  })
  export class FindLinagesGQL extends Apollo.Query<FindLinagesQuery, FindLinagesQueryVariables> {
    document = FindLinagesDocument;
    
  }
export const FindTagsDocument = gql`
    query FindTags($page: Int, $size: Int, $sort: [String]) {
  apiPublicTags(page: $page, size: $size, sort: $sort) {
    id
    name
  }
}
    `;

  @Injectable({
    providedIn: 'root'
  })
  export class FindTagsGQL extends Apollo.Query<FindTagsQuery, FindTagsQueryVariables> {
    document = FindTagsDocument;
    
  }
export const GetLinageDocument = gql`
    query GetLinage($earTagId: Float!) {
  linageVO(earTagId: $earTagId) {
    id
    visibility
    name
    earTagId
    familyname
    polled
    country
    storyHandle
    patriId
    patriName
    patriCountry
  }
}
    `;

  @Injectable({
    providedIn: 'root'
  })
  export class GetLinageGQL extends Apollo.Query<GetLinageQuery, GetLinageQueryVariables> {
    document = GetLinageDocument;
    
  }
export const SearchArticlesDocument = gql`
    query SearchArticles($i18n: I18n!, $query: String!, $categoryIn: [CategoryInListItem], $page: Int, $size: Int, $sort: [String], $isSummary: Boolean = true) {
  apiPublicSearchArticles(categoryIn: $categoryIn, query: $query, i18n: $i18n, page: $page, size: $size, sort: $sort) {
    id
    category
    name
    visibility
    sections {
      id
      title
      body @skip(if: $isSummary)
      ingress
      orderNo
      tags {
        name
      }
      template
      visibility
      ...SectionImageFragment
    }
  }
}
    ${SectionImageFragmentFragmentDoc}`;

  @Injectable({
    providedIn: 'root'
  })
  export class SearchArticlesGQL extends Apollo.Query<SearchArticlesQuery, SearchArticlesQueryVariables> {
    document = SearchArticlesDocument;
    
  }