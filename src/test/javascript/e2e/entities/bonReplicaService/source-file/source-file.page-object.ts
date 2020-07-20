import { element, by, ElementFinder } from 'protractor';

export class SourceFileComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-source-file div table .btn-danger'));
  title = element.all(by.css('jhi-source-file div h2#page-heading span')).first();
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

export class SourceFileUpdatePage {
  pageTitle = element(by.id('jhi-source-file-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));
  zipFileInput = element(by.id('file_zipFile'));
  processedInput = element(by.id('field_processed'));
  outcomeInput = element(by.id('field_outcome'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
  }

  async setZipFileInput(zipFile: string): Promise<void> {
    await this.zipFileInput.sendKeys(zipFile);
  }

  async getZipFileInput(): Promise<string> {
    return await this.zipFileInput.getAttribute('value');
  }

  async setProcessedInput(processed: string): Promise<void> {
    await this.processedInput.sendKeys(processed);
  }

  async getProcessedInput(): Promise<string> {
    return await this.processedInput.getAttribute('value');
  }

  async setOutcomeInput(outcome: string): Promise<void> {
    await this.outcomeInput.sendKeys(outcome);
  }

  async getOutcomeInput(): Promise<string> {
    return await this.outcomeInput.getAttribute('value');
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

export class SourceFileDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-sourceFile-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-sourceFile'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
