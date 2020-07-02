import { element, by, ElementFinder } from 'protractor';

export class StoryComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-story div table .btn-danger'));
  title = element.all(by.css('jhi-story div h2#page-heading span')).first();
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

export class StoryUpdatePage {
  pageTitle = element(by.id('jhi-story-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  categorySelect = element(by.id('field_category'));
  nameInput = element(by.id('field_name'));
  visibilitySelect = element(by.id('field_visibility'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCategorySelect(category: string): Promise<void> {
    await this.categorySelect.sendKeys(category);
  }

  async getCategorySelect(): Promise<string> {
    return await this.categorySelect.element(by.css('option:checked')).getText();
  }

  async categorySelectLastOption(): Promise<void> {
    await this.categorySelect.all(by.tagName('option')).last().click();
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

export class StoryDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-story-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-story'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
