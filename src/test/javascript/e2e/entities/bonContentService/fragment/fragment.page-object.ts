import { element, by, ElementFinder } from 'protractor';

export class FragmentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-fragment div table .btn-danger'));
  title = element.all(by.css('jhi-fragment div h2#page-heading span')).first();
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

export class FragmentUpdatePage {
  pageTitle = element(by.id('jhi-fragment-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  templateSelect = element(by.id('field_template'));
  nameInput = element(by.id('field_name'));
  titleInput = element(by.id('field_title'));
  ingressInput = element(by.id('field_ingress'));
  bodyInput = element(by.id('field_body'));
  imageInput = element(by.id('file_image'));
  captionInput = element(by.id('field_caption'));
  widthInput = element(by.id('field_width'));
  heightInput = element(by.id('field_height'));
  orderNoInput = element(by.id('field_orderNo'));
  visibilitySelect = element(by.id('field_visibility'));

  tagSelect = element(by.id('field_tag'));
  storySelect = element(by.id('field_story'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTemplateSelect(template: string): Promise<void> {
    await this.templateSelect.sendKeys(template);
  }

  async getTemplateSelect(): Promise<string> {
    return await this.templateSelect.element(by.css('option:checked')).getText();
  }

  async templateSelectLastOption(): Promise<void> {
    await this.templateSelect.all(by.tagName('option')).last().click();
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
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

  async setWidthInput(width: string): Promise<void> {
    await this.widthInput.sendKeys(width);
  }

  async getWidthInput(): Promise<string> {
    return await this.widthInput.getAttribute('value');
  }

  async setHeightInput(height: string): Promise<void> {
    await this.heightInput.sendKeys(height);
  }

  async getHeightInput(): Promise<string> {
    return await this.heightInput.getAttribute('value');
  }

  async setOrderNoInput(orderNo: string): Promise<void> {
    await this.orderNoInput.sendKeys(orderNo);
  }

  async getOrderNoInput(): Promise<string> {
    return await this.orderNoInput.getAttribute('value');
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

  async tagSelectLastOption(): Promise<void> {
    await this.tagSelect.all(by.tagName('option')).last().click();
  }

  async tagSelectOption(option: string): Promise<void> {
    await this.tagSelect.sendKeys(option);
  }

  getTagSelect(): ElementFinder {
    return this.tagSelect;
  }

  async getTagSelectedOption(): Promise<string> {
    return await this.tagSelect.element(by.css('option:checked')).getText();
  }

  async storySelectLastOption(): Promise<void> {
    await this.storySelect.all(by.tagName('option')).last().click();
  }

  async storySelectOption(option: string): Promise<void> {
    await this.storySelect.sendKeys(option);
  }

  getStorySelect(): ElementFinder {
    return this.storySelect;
  }

  async getStorySelectedOption(): Promise<string> {
    return await this.storySelect.element(by.css('option:checked')).getText();
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

export class FragmentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-fragment-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-fragment'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
