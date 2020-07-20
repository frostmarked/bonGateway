import { element, by, ElementFinder } from 'protractor';

export class NoteComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-note div table .btn-danger'));
  title = element.all(by.css('jhi-note div h2#page-heading span')).first();
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

export class NoteUpdatePage {
  pageTitle = element(by.id('jhi-note-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  categorySelect = element(by.id('field_category'));
  noteInput = element(by.id('field_note'));
  actualDateInput = element(by.id('field_actualDate'));

  pastureSelect = element(by.id('field_pasture'));
  cattleSelect = element(by.id('field_cattle'));

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

  async setNoteInput(note: string): Promise<void> {
    await this.noteInput.sendKeys(note);
  }

  async getNoteInput(): Promise<string> {
    return await this.noteInput.getAttribute('value');
  }

  async setActualDateInput(actualDate: string): Promise<void> {
    await this.actualDateInput.sendKeys(actualDate);
  }

  async getActualDateInput(): Promise<string> {
    return await this.actualDateInput.getAttribute('value');
  }

  async pastureSelectLastOption(): Promise<void> {
    await this.pastureSelect.all(by.tagName('option')).last().click();
  }

  async pastureSelectOption(option: string): Promise<void> {
    await this.pastureSelect.sendKeys(option);
  }

  getPastureSelect(): ElementFinder {
    return this.pastureSelect;
  }

  async getPastureSelectedOption(): Promise<string> {
    return await this.pastureSelect.element(by.css('option:checked')).getText();
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

export class NoteDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-note-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-note'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
