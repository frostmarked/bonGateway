import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import {
  FragmentComponentsPage,
  /* FragmentDeleteDialog, */
  FragmentUpdatePage,
} from './fragment.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('Fragment e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let fragmentComponentsPage: FragmentComponentsPage;
  let fragmentUpdatePage: FragmentUpdatePage;
  /* let fragmentDeleteDialog: FragmentDeleteDialog; */
  const fileNameToUpload = 'logo-jhipster.png';
  const fileToUpload = '../../../../../../../src/main/webapp/content/images/' + fileNameToUpload;
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Fragments', async () => {
    await navBarPage.goToEntity('fragment');
    fragmentComponentsPage = new FragmentComponentsPage();
    await browser.wait(ec.visibilityOf(fragmentComponentsPage.title), 5000);
    expect(await fragmentComponentsPage.getTitle()).to.eq('bonGatewayApp.bonContentServiceFragment.home.title');
    await browser.wait(ec.or(ec.visibilityOf(fragmentComponentsPage.entities), ec.visibilityOf(fragmentComponentsPage.noResult)), 1000);
  });

  it('should load create Fragment page', async () => {
    await fragmentComponentsPage.clickOnCreateButton();
    fragmentUpdatePage = new FragmentUpdatePage();
    expect(await fragmentUpdatePage.getPageTitle()).to.eq('bonGatewayApp.bonContentServiceFragment.home.createOrEditLabel');
    await fragmentUpdatePage.cancel();
  });

  /* it('should create and save Fragments', async () => {
        const nbButtonsBeforeCreate = await fragmentComponentsPage.countDeleteButtons();

        await fragmentComponentsPage.clickOnCreateButton();

        await promise.all([
            fragmentUpdatePage.templateSelectLastOption(),
            fragmentUpdatePage.setNameInput('name'),
            fragmentUpdatePage.setTitleInput('title'),
            fragmentUpdatePage.setIngressInput('ingress'),
            fragmentUpdatePage.setBodyInput('body'),
            fragmentUpdatePage.setImageInput(absolutePath),
            fragmentUpdatePage.setCaptionInput('caption'),
            fragmentUpdatePage.setWidthInput('5'),
            fragmentUpdatePage.setHeightInput('5'),
            fragmentUpdatePage.setOrderNoInput('5'),
            fragmentUpdatePage.visibilitySelectLastOption(),
            // fragmentUpdatePage.tagSelectLastOption(),
            fragmentUpdatePage.storySelectLastOption(),
        ]);

        expect(await fragmentUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
        expect(await fragmentUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
        expect(await fragmentUpdatePage.getIngressInput()).to.eq('ingress', 'Expected Ingress value to be equals to ingress');
        expect(await fragmentUpdatePage.getBodyInput()).to.eq('body', 'Expected Body value to be equals to body');
        expect(await fragmentUpdatePage.getImageInput()).to.endsWith(fileNameToUpload, 'Expected Image value to be end with ' + fileNameToUpload);
        expect(await fragmentUpdatePage.getCaptionInput()).to.eq('caption', 'Expected Caption value to be equals to caption');
        expect(await fragmentUpdatePage.getWidthInput()).to.eq('5', 'Expected width value to be equals to 5');
        expect(await fragmentUpdatePage.getHeightInput()).to.eq('5', 'Expected height value to be equals to 5');
        expect(await fragmentUpdatePage.getOrderNoInput()).to.eq('5', 'Expected orderNo value to be equals to 5');

        await fragmentUpdatePage.save();
        expect(await fragmentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await fragmentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last Fragment', async () => {
        const nbButtonsBeforeDelete = await fragmentComponentsPage.countDeleteButtons();
        await fragmentComponentsPage.clickOnLastDeleteButton();

        fragmentDeleteDialog = new FragmentDeleteDialog();
        expect(await fragmentDeleteDialog.getDialogTitle())
            .to.eq('bonGatewayApp.bonContentServiceFragment.delete.question');
        await fragmentDeleteDialog.clickOnConfirmButton();

        expect(await fragmentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
