import { element, by, ElementFinder } from 'protractor';

export class LocalizedComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-localized div table .btn-danger'));
  title = element.all(by.css('jhi-localized div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class LocalizedUpdatePage {
  pageTitle = element(by.id('jhi-localized-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  i18nInput = element(by.id('field_i18n'));
  titleInput = element(by.id('field_title'));
  ingressInput = element(by.id('field_ingress'));
  bodyInput = element(by.id('field_body'));
  captionInput = element(by.id('field_caption'));
  visibilitySelect = element(by.id('field_visibility'));

  fragmentSelect = element(by.id('field_fragment'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setI18nInput(i18n: string): Promise<void> {
    await this.i18nInput.sendKeys(i18n);
  }

  async getI18nInput(): Promise<string> {
    return await this.i18nInput.getAttribute('value');
  }

  async setTitleInput(title: string): Promise<void> {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput(): Promise<string> {
    return await this.titleInput.getAttribute('value');
  }

  async setIngressInput(ingress: string): Promise<void> {
    await this.ingressInput.sendKeys(ingress);
  }

  async getIngressInput(): Promise<string> {
    return await this.ingressInput.getAttribute('value');
  }

  async setBodyInput(body: string): Promise<void> {
    await this.bodyInput.sendKeys(body);
  }

  async getBodyInput(): Promise<string> {
    return await this.bodyInput.getAttribute('value');
  }

  async setCaptionInput(caption: string): Promise<void> {
    await this.captionInput.sendKeys(caption);
  }

  async getCaptionInput(): Promise<string> {
    return await this.captionInput.getAttribute('value');
  }

  async setVisibilitySelect(visibility: string): Promise<void> {
    await this.visibilitySelect.sendKeys(visibility);
  }

  async getVisibilitySelect(): Promise<string> {
    return await this.visibilitySelect.element(by.css('option:checked')).getText();
  }

  async visibilitySelectLastOption(): Promise<void> {
    await this.visibilitySelect.all(by.tagName('option')).last().click();
  }

  async fragmentSelectLastOption(): Promise<void> {
    await this.fragmentSelect.all(by.tagName('option')).last().click();
  }

  async fragmentSelectOption(option: string): Promise<void> {
    await this.fragmentSelect.sendKeys(option);
  }

  getFragmentSelect(): ElementFinder {
    return this.fragmentSelect;
  }

  async getFragmentSelectedOption(): Promise<string> {
    return await this.fragmentSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class LocalizedDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-localized-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-localized'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
