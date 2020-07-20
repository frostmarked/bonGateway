import { element, by, ElementFinder } from 'protractor';

export class MatrilinealityComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-matrilineality div table .btn-danger'));
  title = element.all(by.css('jhi-matrilineality div h2#page-heading span')).first();
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

export class MatrilinealityUpdatePage {
  pageTitle = element(by.id('jhi-matrilineality-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  familynameInput = element(by.id('field_familyname'));
  earTagIdInput = element(by.id('field_earTagId'));
  nameInput = element(by.id('field_name'));
  countryInput = element(by.id('field_country'));
  descriptionInput = element(by.id('field_description'));
  cattleNameRegexPatternInput = element(by.id('field_cattleNameRegexPattern'));
  patriIdInput = element(by.id('field_patriId'));
  patriNameInput = element(by.id('field_patriName'));
  patriCountryInput = element(by.id('field_patriCountry'));
  polledInput = element(by.id('field_polled'));
  storyHandleInput = element(by.id('field_storyHandle'));
  visibilitySelect = element(by.id('field_visibility'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setFamilynameInput(familyname: string): Promise<void> {
    await this.familynameInput.sendKeys(familyname);
  }

  async getFamilynameInput(): Promise<string> {
    return await this.familynameInput.getAttribute('value');
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

  async setCountryInput(country: string): Promise<void> {
    await this.countryInput.sendKeys(country);
  }

  async getCountryInput(): Promise<string> {
    return await this.countryInput.getAttribute('value');
  }

  async setDescriptionInput(description: string): Promise<void> {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput(): Promise<string> {
    return await this.descriptionInput.getAttribute('value');
  }

  async setCattleNameRegexPatternInput(cattleNameRegexPattern: string): Promise<void> {
    await this.cattleNameRegexPatternInput.sendKeys(cattleNameRegexPattern);
  }

  async getCattleNameRegexPatternInput(): Promise<string> {
    return await this.cattleNameRegexPatternInput.getAttribute('value');
  }

  async setPatriIdInput(patriId: string): Promise<void> {
    await this.patriIdInput.sendKeys(patriId);
  }

  async getPatriIdInput(): Promise<string> {
    return await this.patriIdInput.getAttribute('value');
  }

  async setPatriNameInput(patriName: string): Promise<void> {
    await this.patriNameInput.sendKeys(patriName);
  }

  async getPatriNameInput(): Promise<string> {
    return await this.patriNameInput.getAttribute('value');
  }

  async setPatriCountryInput(patriCountry: string): Promise<void> {
    await this.patriCountryInput.sendKeys(patriCountry);
  }

  async getPatriCountryInput(): Promise<string> {
    return await this.patriCountryInput.getAttribute('value');
  }

  getPolledInput(): ElementFinder {
    return this.polledInput;
  }

  async setStoryHandleInput(storyHandle: string): Promise<void> {
    await this.storyHandleInput.sendKeys(storyHandle);
  }

  async getStoryHandleInput(): Promise<string> {
    return await this.storyHandleInput.getAttribute('value');
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

export class MatrilinealityDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-matrilineality-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-matrilineality'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
