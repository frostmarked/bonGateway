import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import {
  LocalizedComponentsPage,
  /* LocalizedDeleteDialog, */
  LocalizedUpdatePage,
} from './localized.page-object';

const expect = chai.expect;

describe('Localized e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let localizedComponentsPage: LocalizedComponentsPage;
  let localizedUpdatePage: LocalizedUpdatePage;
  /* let localizedDeleteDialog: LocalizedDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Localizeds', async () => {
    await navBarPage.goToEntity('localized');
    localizedComponentsPage = new LocalizedComponentsPage();
    await browser.wait(ec.visibilityOf(localizedComponentsPage.title), 5000);
    expect(await localizedComponentsPage.getTitle()).to.eq('bonGatewayApp.bonContentServiceLocalized.home.title');
    await browser.wait(ec.or(ec.visibilityOf(localizedComponentsPage.entities), ec.visibilityOf(localizedComponentsPage.noResult)), 1000);
  });

  it('should load create Localized page', async () => {
    await localizedComponentsPage.clickOnCreateButton();
    localizedUpdatePage = new LocalizedUpdatePage();
    expect(await localizedUpdatePage.getPageTitle()).to.eq('bonGatewayApp.bonContentServiceLocalized.home.createOrEditLabel');
    await localizedUpdatePage.cancel();
  });

  /* it('should create and save Localizeds', async () => {
        const nbButtonsBeforeCreate = await localizedComponentsPage.countDeleteButtons();

        await localizedComponentsPage.clickOnCreateButton();

        await promise.all([
            localizedUpdatePage.setI18nInput('nrnz'),
            localizedUpdatePage.setTitleInput('title'),
            localizedUpdatePage.setIngressInput('ingress'),
            localizedUpdatePage.setBodyInput('body'),
            localizedUpdatePage.setCaptionInput('caption'),
            localizedUpdatePage.visibilitySelectLastOption(),
            localizedUpdatePage.fragmentSelectLastOption(),
        ]);

        expect(await localizedUpdatePage.getI18nInput()).to.eq('nrnz', 'Expected I18n value to be equals to nrnz');
        expect(await localizedUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
        expect(await localizedUpdatePage.getIngressInput()).to.eq('ingress', 'Expected Ingress value to be equals to ingress');
        expect(await localizedUpdatePage.getBodyInput()).to.eq('body', 'Expected Body value to be equals to body');
        expect(await localizedUpdatePage.getCaptionInput()).to.eq('caption', 'Expected Caption value to be equals to caption');

        await localizedUpdatePage.save();
        expect(await localizedUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await localizedComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last Localized', async () => {
        const nbButtonsBeforeDelete = await localizedComponentsPage.countDeleteButtons();
        await localizedComponentsPage.clickOnLastDeleteButton();

        localizedDeleteDialog = new LocalizedDeleteDialog();
        expect(await localizedDeleteDialog.getDialogTitle())
            .to.eq('bonGatewayApp.bonContentServiceLocalized.delete.question');
        await localizedDeleteDialog.clickOnConfirmButton();

        expect(await localizedComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
