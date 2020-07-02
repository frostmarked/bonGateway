import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { PastureComponentsPage, PastureDeleteDialog, PastureUpdatePage } from './pasture.page-object';

const expect = chai.expect;

describe('Pasture e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let pastureComponentsPage: PastureComponentsPage;
  let pastureUpdatePage: PastureUpdatePage;
  let pastureDeleteDialog: PastureDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Pastures', async () => {
    await navBarPage.goToEntity('pasture');
    pastureComponentsPage = new PastureComponentsPage();
    await browser.wait(ec.visibilityOf(pastureComponentsPage.title), 5000);
    expect(await pastureComponentsPage.getTitle()).to.eq('bonGatewayApp.bonLivestockServicePasture.home.title');
    await browser.wait(ec.or(ec.visibilityOf(pastureComponentsPage.entities), ec.visibilityOf(pastureComponentsPage.noResult)), 1000);
  });

  it('should load create Pasture page', async () => {
    await pastureComponentsPage.clickOnCreateButton();
    pastureUpdatePage = new PastureUpdatePage();
    expect(await pastureUpdatePage.getPageTitle()).to.eq('bonGatewayApp.bonLivestockServicePasture.home.createOrEditLabel');
    await pastureUpdatePage.cancel();
  });

  it('should create and save Pastures', async () => {
    const nbButtonsBeforeCreate = await pastureComponentsPage.countDeleteButtons();

    await pastureComponentsPage.clickOnCreateButton();

    await promise.all([pastureUpdatePage.setNameInput('name'), pastureUpdatePage.setDescriptionInput('description')]);

    expect(await pastureUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await pastureUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');

    await pastureUpdatePage.save();
    expect(await pastureUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await pastureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Pasture', async () => {
    const nbButtonsBeforeDelete = await pastureComponentsPage.countDeleteButtons();
    await pastureComponentsPage.clickOnLastDeleteButton();

    pastureDeleteDialog = new PastureDeleteDialog();
    expect(await pastureDeleteDialog.getDialogTitle()).to.eq('bonGatewayApp.bonLivestockServicePasture.delete.question');
    await pastureDeleteDialog.clickOnConfirmButton();

    expect(await pastureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
