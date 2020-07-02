import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { CattleComponentsPage, CattleDeleteDialog, CattleUpdatePage } from './cattle.page-object';

const expect = chai.expect;

describe('Cattle e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let cattleComponentsPage: CattleComponentsPage;
  let cattleUpdatePage: CattleUpdatePage;
  let cattleDeleteDialog: CattleDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Cattles', async () => {
    await navBarPage.goToEntity('cattle');
    cattleComponentsPage = new CattleComponentsPage();
    await browser.wait(ec.visibilityOf(cattleComponentsPage.title), 5000);
    expect(await cattleComponentsPage.getTitle()).to.eq('bonGatewayApp.bonLivestockServiceCattle.home.title');
    await browser.wait(ec.or(ec.visibilityOf(cattleComponentsPage.entities), ec.visibilityOf(cattleComponentsPage.noResult)), 1000);
  });

  it('should load create Cattle page', async () => {
    await cattleComponentsPage.clickOnCreateButton();
    cattleUpdatePage = new CattleUpdatePage();
    expect(await cattleUpdatePage.getPageTitle()).to.eq('bonGatewayApp.bonLivestockServiceCattle.home.createOrEditLabel');
    await cattleUpdatePage.cancel();
  });

  it('should create and save Cattles', async () => {
    const nbButtonsBeforeCreate = await cattleComponentsPage.countDeleteButtons();

    await cattleComponentsPage.clickOnCreateButton();

    await promise.all([
      cattleUpdatePage.setEarTagIdInput('5'),
      cattleUpdatePage.setNameInput('name'),
      cattleUpdatePage.visibilitySelectLastOption(),
      cattleUpdatePage.setStoryHandleInput('storyHandle'),
      cattleUpdatePage.matrilinealitySelectLastOption(),
    ]);

    expect(await cattleUpdatePage.getEarTagIdInput()).to.eq('5', 'Expected earTagId value to be equals to 5');
    expect(await cattleUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    const selectedUpForSale = cattleUpdatePage.getUpForSaleInput();
    if (await selectedUpForSale.isSelected()) {
      await cattleUpdatePage.getUpForSaleInput().click();
      expect(await cattleUpdatePage.getUpForSaleInput().isSelected(), 'Expected upForSale not to be selected').to.be.false;
    } else {
      await cattleUpdatePage.getUpForSaleInput().click();
      expect(await cattleUpdatePage.getUpForSaleInput().isSelected(), 'Expected upForSale to be selected').to.be.true;
    }
    const selectedShowBlup = cattleUpdatePage.getShowBlupInput();
    if (await selectedShowBlup.isSelected()) {
      await cattleUpdatePage.getShowBlupInput().click();
      expect(await cattleUpdatePage.getShowBlupInput().isSelected(), 'Expected showBlup not to be selected').to.be.false;
    } else {
      await cattleUpdatePage.getShowBlupInput().click();
      expect(await cattleUpdatePage.getShowBlupInput().isSelected(), 'Expected showBlup to be selected').to.be.true;
    }
    const selectedAlert = cattleUpdatePage.getAlertInput();
    if (await selectedAlert.isSelected()) {
      await cattleUpdatePage.getAlertInput().click();
      expect(await cattleUpdatePage.getAlertInput().isSelected(), 'Expected alert not to be selected').to.be.false;
    } else {
      await cattleUpdatePage.getAlertInput().click();
      expect(await cattleUpdatePage.getAlertInput().isSelected(), 'Expected alert to be selected').to.be.true;
    }
    expect(await cattleUpdatePage.getStoryHandleInput()).to.eq('storyHandle', 'Expected StoryHandle value to be equals to storyHandle');

    await cattleUpdatePage.save();
    expect(await cattleUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await cattleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Cattle', async () => {
    const nbButtonsBeforeDelete = await cattleComponentsPage.countDeleteButtons();
    await cattleComponentsPage.clickOnLastDeleteButton();

    cattleDeleteDialog = new CattleDeleteDialog();
    expect(await cattleDeleteDialog.getDialogTitle()).to.eq('bonGatewayApp.bonLivestockServiceCattle.delete.question');
    await cattleDeleteDialog.clickOnConfirmButton();

    expect(await cattleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
