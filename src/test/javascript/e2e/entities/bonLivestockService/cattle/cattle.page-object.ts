import { element, by, ElementFinder } from 'protractor';

export class CattleComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-cattle div table .btn-danger'));
  title = element.all(by.css('jhi-cattle div h2#page-heading span')).first();
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

export class CattleUpdatePage {
  pageTitle = element(by.id('jhi-cattle-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  earTagIdInput = element(by.id('field_earTagId'));
  nameInput = element(by.id('field_name'));
  visibilitySelect = element(by.id('field_visibility'));
  upForSaleInput = element(by.id('field_upForSale'));
  showBlupInput = element(by.id('field_showBlup'));
  alertInput = element(by.id('field_alert'));
  storyHandleInput = element(by.id('field_storyHandle'));

  matrilinealitySelect = element(by.id('field_matrilineality'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setEarTagIdInput(earTagId: string): Promise<void> {
    await this.earTagIdInput.sendKeys(earTagId);
  }

  async getEarTagIdInput(): Promise<string> {
    return await this.earTagIdInput.getAttribute('value');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
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

  getUpForSaleInput(): ElementFinder {
    return this.upForSaleInput;
  }

  getShowBlupInput(): ElementFinder {
    return this.showBlupInput;
  }

  getAlertInput(): ElementFinder {
    return this.alertInput;
  }

  async setStoryHandleInput(storyHandle: string): Promise<void> {
    await this.storyHandleInput.sendKeys(storyHandle);
  }

  async getStoryHandleInput(): Promise<string> {
    return await this.storyHandleInput.getAttribute('value');
  }

  async matrilinealitySelectLastOption(): Promise<void> {
    await this.matrilinealitySelect.all(by.tagName('option')).last().click();
  }

  async matrilinealitySelectOption(option: string): Promise<void> {
    await this.matrilinealitySelect.sendKeys(option);
  }

  getMatrilinealitySelect(): ElementFinder {
    return this.matrilinealitySelect;
  }

  async getMatrilinealitySelectedOption(): Promise<string> {
    return await this.matrilinealitySelect.element(by.css('option:checked')).getText();
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

export class CattleDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-cattle-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-cattle'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
