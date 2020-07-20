import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import {
  NoteComponentsPage,
  /* NoteDeleteDialog, */
  NoteUpdatePage,
} from './note.page-object';

const expect = chai.expect;

describe('Note e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let noteComponentsPage: NoteComponentsPage;
  let noteUpdatePage: NoteUpdatePage;
  /* let noteDeleteDialog: NoteDeleteDialog; */

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Notes', async () => {
    await navBarPage.goToEntity('note');
    noteComponentsPage = new NoteComponentsPage();
    await browser.wait(ec.visibilityOf(noteComponentsPage.title), 5000);
    expect(await noteComponentsPage.getTitle()).to.eq('bonGatewayApp.bonLivestockServiceNote.home.title');
    await browser.wait(ec.or(ec.visibilityOf(noteComponentsPage.entities), ec.visibilityOf(noteComponentsPage.noResult)), 1000);
  });

  it('should load create Note page', async () => {
    await noteComponentsPage.clickOnCreateButton();
    noteUpdatePage = new NoteUpdatePage();
    expect(await noteUpdatePage.getPageTitle()).to.eq('bonGatewayApp.bonLivestockServiceNote.home.createOrEditLabel');
    await noteUpdatePage.cancel();
  });

  /* it('should create and save Notes', async () => {
        const nbButtonsBeforeCreate = await noteComponentsPage.countDeleteButtons();

        await noteComponentsPage.clickOnCreateButton();

        await promise.all([
            noteUpdatePage.categorySelectLastOption(),
            noteUpdatePage.setNoteInput('note'),
            noteUpdatePage.setActualDateInput('2000-12-31'),
            noteUpdatePage.pastureSelectLastOption(),
            noteUpdatePage.cattleSelectLastOption(),
        ]);

        expect(await noteUpdatePage.getNoteInput()).to.eq('note', 'Expected Note value to be equals to note');
        expect(await noteUpdatePage.getActualDateInput()).to.eq('2000-12-31', 'Expected actualDate value to be equals to 2000-12-31');

        await noteUpdatePage.save();
        expect(await noteUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await noteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

  /* it('should delete last Note', async () => {
        const nbButtonsBeforeDelete = await noteComponentsPage.countDeleteButtons();
        await noteComponentsPage.clickOnLastDeleteButton();

        noteDeleteDialog = new NoteDeleteDialog();
        expect(await noteDeleteDialog.getDialogTitle())
            .to.eq('bonGatewayApp.bonLivestockServiceNote.delete.question');
        await noteDeleteDialog.clickOnConfirmButton();

        expect(await noteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
