import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { StoryComponentsPage, StoryDeleteDialog, StoryUpdatePage } from './story.page-object';

const expect = chai.expect;

describe('Story e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let storyComponentsPage: StoryComponentsPage;
  let storyUpdatePage: StoryUpdatePage;
  let storyDeleteDialog: StoryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Stories', async () => {
    await navBarPage.goToEntity('story');
    storyComponentsPage = new StoryComponentsPage();
    await browser.wait(ec.visibilityOf(storyComponentsPage.title), 5000);
    expect(await storyComponentsPage.getTitle()).to.eq('bonGatewayApp.bonContentServiceStory.home.title');
    await browser.wait(ec.or(ec.visibilityOf(storyComponentsPage.entities), ec.visibilityOf(storyComponentsPage.noResult)), 1000);
  });

  it('should load create Story page', async () => {
    await storyComponentsPage.clickOnCreateButton();
    storyUpdatePage = new StoryUpdatePage();
    expect(await storyUpdatePage.getPageTitle()).to.eq('bonGatewayApp.bonContentServiceStory.home.createOrEditLabel');
    await storyUpdatePage.cancel();
  });

  it('should create and save Stories', async () => {
    const nbButtonsBeforeCreate = await storyComponentsPage.countDeleteButtons();

    await storyComponentsPage.clickOnCreateButton();

    await promise.all([
      storyUpdatePage.categorySelectLastOption(),
      storyUpdatePage.setNameInput('name'),
      storyUpdatePage.visibilitySelectLastOption(),
    ]);

    expect(await storyUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');

    await storyUpdatePage.save();
    expect(await storyUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await storyComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Story', async () => {
    const nbButtonsBeforeDelete = await storyComponentsPage.countDeleteButtons();
    await storyComponentsPage.clickOnLastDeleteButton();

    storyDeleteDialog = new StoryDeleteDialog();
    expect(await storyDeleteDialog.getDialogTitle()).to.eq('bonGatewayApp.bonContentServiceStory.delete.question');
    await storyDeleteDialog.clickOnConfirmButton();

    expect(await storyComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
