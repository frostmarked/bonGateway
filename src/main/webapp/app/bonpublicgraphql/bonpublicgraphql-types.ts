

/* tslint:disable */
/* eslint-disable */
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL query operation: GetArticle
// ====================================================

export interface GetArticle_articleVO_sections_tags {
  name: string | null;
}

export interface GetArticle_articleVO_sections {
  id: number | null;
  body: string | null;
  ingress: string | null;
  orderNo: number | null;
  tags: (GetArticle_articleVO_sections_tags | null)[] | null;
  template: Template | null;
  title: string | null;
  visibility: Visibility | null;
  caption: string | null;
  width: number | null;
  height: number | null;
  image: string | null;
  imageContentType: string | null;
}

export interface GetArticle_articleVO {
  id: number | null;
  category: Category | null;
  name: string | null;
  visibility: Visibility | null;
  sections: (GetArticle_articleVO_sections | null)[] | null;
}

export interface GetArticle {
  articleVO: GetArticle_articleVO | null;  // Get ArticleVO by Id or handle  Equivalent to GET /api/public/articles/{id}
}

export interface GetArticleVariables {
  i18n: I18n;
  id: string;
  isHandle?: boolean | null;
  isSummary?: boolean | null;
}


/* tslint:disable */
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL query operation: GetCow
// ====================================================

export interface GetCow_cowVO {
  birthDate: string | null;
  earTagId: number | null;
  gender: Gender | null;
  hornStatus: HornStatus | null;
  linageId: number | null;
  linageName: string | null;
  matriId: number | null;
  name: string | null;
  patriId: number | null;
  storyHandle: string | null;
  visibility: Visibility | null;
  weight0: number | null;
  weight200: number | null;
  weight365: number | null;
}

export interface GetCow {
  cowVO: GetCow_cowVO | null;  // getCowVO  Equivalent to GET /api/public/cows/{earTagId}
}

export interface GetCowVariables {
  earTagId: number;
}


/* tslint:disable */
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL query operation: FindArticles
// ====================================================

export interface FindArticles_apiPublicArticles_sections_tags {
  name: string | null;
}

export interface FindArticles_apiPublicArticles_sections {
  id: number | null;
  title: string | null;
  body: string | null;
  ingress: string | null;
  orderNo: number | null;
  tags: (FindArticles_apiPublicArticles_sections_tags | null)[] | null;
  template: Template | null;
  visibility: Visibility | null;
  caption: string | null;
  width: number | null;
  height: number | null;
  image: string | null;
  imageContentType: string | null;
}

export interface FindArticles_apiPublicArticles {
  id: number | null;
  category: Category | null;
  name: string | null;
  visibility: Visibility | null;
  sections: (FindArticles_apiPublicArticles_sections | null)[] | null;
}

export interface FindArticles {
  apiPublicArticles: (FindArticles_apiPublicArticles | null)[] | null;  // Get all ArticleVOs  Equivalent to GET /api/public/articles
}

export interface FindArticlesVariables {
  i18n: I18n;
  categoryIn?: (CategoryInListItem | null)[] | null;
  page?: number | null;
  size?: number | null;
  sort?: (string | null)[] | null;
  isSummary?: boolean | null;
}


/* tslint:disable */
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL query operation: FindCowPhotos
// ====================================================

export interface FindCowPhotos_apiPublicCowPhotographs {
  caption: string | null;
  earTagId: number | null;
  height: number | null;
  id: number | null;
  image: string | null;
  imageContentType: string | null;
  taken: string | null;
  visibility: Visibility | null;
  width: number | null;
}

export interface FindCowPhotos {
  apiPublicCowPhotographs: (FindCowPhotos_apiPublicCowPhotographs | null)[] | null;  // Get all PhotographVOs for a cow  Equivalent to GET /api/public/cows/{earTagId}/photographs
}

export interface FindCowPhotosVariables {
  earTagId: number;
  page?: number | null;
  size?: number | null;
  sort?: (string | null)[] | null;
  excludeImage?: boolean | null;
}


/* tslint:disable */
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL query operation: FindCows
// ====================================================

export interface FindCows_apiPublicCows {
  birthDate: string | null;
  earTagId: number | null;
  gender: Gender | null;
  hornStatus: HornStatus | null;
  linageId: number | null;
  linageName: string | null;
  matriId: number | null;
  name: string | null;
  patriId: number | null;
  storyHandle: string | null;
  visibility: Visibility | null;
  weight0: number | null;
  weight200: number | null;
  weight365: number | null;
}

export interface FindCows {
  apiPublicCows: (FindCows_apiPublicCows | null)[] | null;  // Find all cows matching criteria  Equivalent to GET /api/public/cows
}

export interface FindCowsVariables {
  page?: number | null;
  size?: number | null;
  sort?: (string | null)[] | null;
  birthDateGreaterThan?: string | null;
  birthDateLessThan?: string | null;
  genderEquals?: GenderEquals | null;
  hornStatusIn?: (HornStatusInListItem | null)[] | null;
  linageIdEquals?: number | null;
  matriIdEquals?: number | null;
  patriIdEquals?: number | null;
  weight0GreaterThan?: number | null;
  weight0LessThan?: number | null;
  weight0Specified?: boolean | null;
  weight200GreaterThan?: number | null;
  weight200LessThan?: number | null;
  weight200Specified?: boolean | null;
  weight365GreaterThan?: number | null;
  weight365LessThan?: number | null;
  weight365Specified?: boolean | null;
}


/* tslint:disable */
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL query operation: FindLinages
// ====================================================

export interface FindLinages_apiPublicLinages {
  id: number | null;
  visibility: Visibility | null;
  name: string | null;
  earTagId: number | null;
  familyname: string | null;
  polled: boolean | null;
  country: string | null;
}

export interface FindLinages {
  apiPublicLinages: (FindLinages_apiPublicLinages | null)[] | null;  // Find all LinageVOs  Equivalent to GET /api/public/linages
}

export interface FindLinagesVariables {
  page?: number | null;
  size?: number | null;
  sort?: (string | null)[] | null;
}


/* tslint:disable */
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL query operation: SearchArticles
// ====================================================

export interface SearchArticles_apiPublicSearchArticles_sections_tags {
  name: string | null;
}

export interface SearchArticles_apiPublicSearchArticles_sections {
  id: number | null;
  title: string | null;
  body: string | null;
  ingress: string | null;
  orderNo: number | null;
  tags: (SearchArticles_apiPublicSearchArticles_sections_tags | null)[] | null;
  template: Template | null;
  visibility: Visibility | null;
  caption: string | null;
  width: number | null;
  height: number | null;
  image: string | null;
  imageContentType: string | null;
}

export interface SearchArticles_apiPublicSearchArticles {
  id: number | null;
  category: Category | null;
  name: string | null;
  visibility: Visibility | null;
  sections: (SearchArticles_apiPublicSearchArticles_sections | null)[] | null;
}

export interface SearchArticles {
  apiPublicSearchArticles: (SearchArticles_apiPublicSearchArticles | null)[] | null;  // Search ArticleVOs  Equivalent to GET /api/public/_search/articles
}

export interface SearchArticlesVariables {
  i18n: I18n;
  query: string;
  categoryIn?: (CategoryInListItem | null)[] | null;
  page?: number | null;
  size?: number | null;
  sort?: (string | null)[] | null;
  isSummary?: boolean | null;
}


/* tslint:disable */
// This file was automatically generated and should not be edited.

// ====================================================
// GraphQL fragment: SectionImageFragment
// ====================================================

export interface SectionImageFragment {
  caption: string | null;
  width: number | null;
  height: number | null;
  image: string | null;
  imageContentType: string | null;
}

/* tslint:disable */
// This file was automatically generated and should not be edited.

//==============================================================
// START Enums and Input Objects
//==============================================================

export enum I18n {
  EN = "EN",
  SV = "SV",
}

export enum Category {
  CATTLE = "CATTLE",
  IT = "IT",
  MATRILINEALITY = "MATRILINEALITY",
  NEWS = "NEWS",
}

export enum Visibility {
  ROLE_ADMIN = "ROLE_ADMIN",
  ROLE_ANONYMOUS = "ROLE_ANONYMOUS",
  ROLE_USER = "ROLE_USER",
}

export enum Template {
  V1 = "V1",
  V2 = "V2",
  V3 = "V3",
}

export enum Gender {
  BULL = "BULL",
  HEIFER = "HEIFER",
}

export enum HornStatus {
  DEHORNED = "DEHORNED",
  DISBUDDED = "DISBUDDED",
  HORNED = "HORNED",
  POLLED = "POLLED",
  SCURS = "SCURS",
  UNKNOWN = "UNKNOWN",
}

export enum CategoryInListItem {
  CATTLE = "CATTLE",
  IT = "IT",
  MATRILINEALITY = "MATRILINEALITY",
  NEWS = "NEWS",
}

export enum GenderEquals {
  BULL = "BULL",
  HEIFER = "HEIFER",
}

export enum HornStatusInListItem {
  DEHORNED = "DEHORNED",
  DISBUDDED = "DISBUDDED",
  HORNED = "HORNED",
  POLLED = "POLLED",
  SCURS = "SCURS",
  UNKNOWN = "UNKNOWN",
}

//==============================================================
// END Enums and Input Objects
//==============================================================