import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { SourceFileComponentsPage, SourceFileDeleteDialog, SourceFileUpdatePage } from './source-file.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('SourceFile e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let sourceFileComponentsPage: SourceFileComponentsPage;
  let sourceFileUpdatePage: SourceFileUpdatePage;
  let sourceFileDeleteDialog: SourceFileDeleteDialog;
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

  it('should load SourceFiles', async () => {
    await navBarPage.goToEntity('source-file');
    sourceFileComponentsPage = new SourceFileComponentsPage();
    await browser.wait(ec.visibilityOf(sourceFileComponentsPage.title), 5000);
    expect(await sourceFileComponentsPage.getTitle()).to.eq('bonGatewayApp.bonReplicaServiceSourceFile.home.title');
    await browser.wait(ec.or(ec.visibilityOf(sourceFileComponentsPage.entities), ec.visibilityOf(sourceFileComponentsPage.noResult)), 1000);
  });

  it('should load create SourceFile page', async () => {
    await sourceFileComponentsPage.clickOnCreateButton();
    sourceFileUpdatePage = new SourceFileUpdatePage();
    expect(await sourceFileUpdatePage.getPageTitle()).to.eq('bonGatewayApp.bonReplicaServiceSourceFile.home.createOrEditLabel');
    await sourceFileUpdatePage.cancel();
  });

  it('should create and save SourceFiles', async () => {
    const nbButtonsBeforeCreate = await sourceFileComponentsPage.countDeleteButtons();

    await sourceFileComponentsPage.clickOnCreateButton();

    await promise.all([
      sourceFileUpdatePage.setNameInput('name'),
      sourceFileUpdatePage.setZipFileInput(absolutePath),
      sourceFileUpdatePage.setProcessedInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      sourceFileUpdatePage.setOutcomeInput('outcome'),
    ]);

    expect(await sourceFileUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await sourceFileUpdatePage.getZipFileInput()).to.endsWith(
      fileNameToUpload,
      'Expected ZipFile value to be end with ' + fileNameToUpload
    );
    expect(await sourceFileUpdatePage.getProcessedInput()).to.contain(
      '2001-01-01T02:30',
      'Expected processed value to be equals to 2000-12-31'
    );
    expect(await sourceFileUpdatePage.getOutcomeInput()).to.eq('outcome', 'Expected Outcome value to be equals to outcome');

    await sourceFileUpdatePage.save();
    expect(await sourceFileUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await sourceFileComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last SourceFile', async () => {
    const nbButtonsBeforeDelete = await sourceFileComponentsPage.countDeleteButtons();
    await sourceFileComponentsPage.clickOnLastDeleteButton();

    sourceFileDeleteDialog = new SourceFileDeleteDialog();
    expect(await sourceFileDeleteDialog.getDialogTitle()).to.eq('bonGatewayApp.bonReplicaServiceSourceFile.delete.question');
    await sourceFileDeleteDialog.clickOnConfirmButton();

    expect(await sourceFileComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
