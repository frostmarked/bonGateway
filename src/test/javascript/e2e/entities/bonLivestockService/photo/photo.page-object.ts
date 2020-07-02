import { element, by, ElementFinder } from 'protractor';

export class PhotoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-photo div table .btn-danger'));
  title = element.all(by.css('jhi-photo div h2#page-heading span')).first();
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

export class PhotoUpdatePage {
  pageTitle = element(by.id('jhi-photo-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  imageInput = element(by.id('file_image'));
  captionInput = element(by.id('field_caption'));
  heightInput = element(by.id('field_height'));
  widthInput = element(by.id('field_width'));
  takenInput = element(by.id('field_taken'));
  visibilitySelect = element(by.id('field_visibility'));

  cattleSelect = element(by.id('field_cattle'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setImageInput(image: string): Promise<void> {
    await this.imageInput.sendKeys(image);
  }

  async getImageInput(): Promise<string> {
    return await this.imageInput.getAttribute('value');
  }

  async setCaptionInput(caption: string): Promise<void> {
    await this.captionInput.sendKeys(caption);
  }

  async getCaptionInput(): Promise<string> {
    return await this.captionInput.getAttribute('value');
  }

  async setHeightInput(height: string): Promise<void> {
    await this.heightInput.sendKeys(height);
  }

  async getHeightInput(): Promise<string> {
    return await this.heightInput.getAttribute('value');
  }

  async setWidthInput(width: string): Promise<void> {
    await this.widthInput.sendKeys(width);
  }

  async getWidthInput(): Promise<string> {
    return await this.widthInput.getAttribute('value');
  }

  async setTakenInput(taken: string): Promise<void> {
    await this.takenInput.sendKeys(taken);
  }

  async getTakenInput(): Promise<string> {
    return await this.takenInput.getAttribute('value');
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

  async cattleSelectLastOption(): Promise<void> {
    await this.cattleSelect.all(by.tagName('option')).last().click();
  }

  async cattleSelectOption(option: string): Promise<void> {
    await this.cattleSelect.sendKeys(option);
  }

  getCattleSelect(): ElementFinder {
    return this.cattleSelect;
  }

  async getCattleSelectedOption(): Promise<string> {
    return await this.cattleSelect.element(by.css('option:checked')).getText();
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

export class PhotoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-photo-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-photo'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
