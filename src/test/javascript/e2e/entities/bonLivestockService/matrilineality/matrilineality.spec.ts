import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { MatrilinealityComponentsPage, MatrilinealityDeleteDialog, MatrilinealityUpdatePage } from './matrilineality.page-object';

const expect = chai.expect;

describe('Matrilineality e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let matrilinealityComponentsPage: MatrilinealityComponentsPage;
  let matrilinealityUpdatePage: MatrilinealityUpdatePage;
  let matrilinealityDeleteDialog: MatrilinealityDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Matrilinealities', async () => {
    await navBarPage.goToEntity('matrilineality');
    matrilinealityComponentsPage = new MatrilinealityComponentsPage();
    await browser.wait(ec.visibilityOf(matrilinealityComponentsPage.title), 5000);
    expect(await matrilinealityComponentsPage.getTitle()).to.eq('bonGatewayApp.bonLivestockServiceMatrilineality.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(matrilinealityComponentsPage.entities), ec.visibilityOf(matrilinealityComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Matrilineality page', async () => {
    await matrilinealityComponentsPage.clickOnCreateButton();
    matrilinealityUpdatePage = new MatrilinealityUpdatePage();
    expect(await matrilinealityUpdatePage.getPageTitle()).to.eq('bonGatewayApp.bonLivestockServiceMatrilineality.home.createOrEditLabel');
    await matrilinealityUpdatePage.cancel();
  });

  it('should create and save Matrilinealities', async () => {
    const nbButtonsBeforeCreate = await matrilinealityComponentsPage.countDeleteButtons();

    await matrilinealityComponentsPage.clickOnCreateButton();

    await promise.all([
      matrilinealityUpdatePage.setFamilynameInput('familyname'),
      matrilinealityUpdatePage.setEarTagIdInput('5'),
      matrilinealityUpdatePage.setNameInput('name'),
      matrilinealityUpdatePage.setCountryInput('country'),
      matrilinealityUpdatePage.setDescriptionInput('description'),
      matrilinealityUpdatePage.setCattleNameRegexPatternInput('cattleNameRegexPattern'),
      matrilinealityUpdatePage.setPatriIdInput('5'),
      matrilinealityUpdatePage.setPatriNameInput('patriName'),
      matrilinealityUpdatePage.setPatriCountryInput('patriCountry'),
      matrilinealityUpdatePage.setStoryHandleInput('storyHandle'),
      matrilinealityUpdatePage.visibilitySelectLastOption(),
    ]);

    expect(await matrilinealityUpdatePage.getFamilynameInput()).to.eq('familyname', 'Expected Familyname value to be equals to familyname');
    expect(await matrilinealityUpdatePage.getEarTagIdInput()).to.eq('5', 'Expected earTagId value to be equals to 5');
    expect(await matrilinealityUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await matrilinealityUpdatePage.getCountryInput()).to.eq('country', 'Expected Country value to be equals to country');
    expect(await matrilinealityUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );
    expect(await matrilinealityUpdatePage.getCattleNameRegexPatternInput()).to.eq(
      'cattleNameRegexPattern',
      'Expected CattleNameRegexPattern value to be equals to cattleNameRegexPattern'
    );
    expect(await matrilinealityUpdatePage.getPatriIdInput()).to.eq('5', 'Expected patriId value to be equals to 5');
    expect(await matrilinealityUpdatePage.getPatriNameInput()).to.eq('patriName', 'Expected PatriName value to be equals to patriName');
    expect(await matrilinealityUpdatePage.getPatriCountryInput()).to.eq(
      'patriCountry',
      'Expected PatriCountry value to be equals to patriCountry'
    );
    const selectedPolled = matrilinealityUpdatePage.getPolledInput();
    if (await selectedPolled.isSelected()) {
      await matrilinealityUpdatePage.getPolledInput().click();
      expect(await matrilinealityUpdatePage.getPolledInput().isSelected(), 'Expected polled not to be selected').to.be.false;
    } else {
      await matrilinealityUpdatePage.getPolledInput().click();
      expect(await matrilinealityUpdatePage.getPolledInput().isSelected(), 'Expected polled to be selected').to.be.true;
    }
    expect(await matrilinealityUpdatePage.getStoryHandleInput()).to.eq(
      'storyHandle',
      'Expected StoryHandle value to be equals to storyHandle'
    );

    await matrilinealityUpdatePage.save();
    expect(await matrilinealityUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await matrilinealityComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last Matrilineality', async () => {
    const nbButtonsBeforeDelete = await matrilinealityComponentsPage.countDeleteButtons();
    await matrilinealityComponentsPage.clickOnLastDeleteButton();

    matrilinealityDeleteDialog = new MatrilinealityDeleteDialog();
    expect(await matrilinealityDeleteDialog.getDialogTitle()).to.eq('bonGatewayApp.bonLivestockServiceMatrilineality.delete.question');
    await matrilinealityDeleteDialog.clickOnConfirmButton();

    expect(await matrilinealityComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
