import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { JournalEntryComponentsPage } from './journal-entry.page-object';

const expect = chai.expect;

describe('JournalEntry e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let journalEntryComponentsPage: JournalEntryComponentsPage;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load JournalEntries', async () => {
    await navBarPage.goToEntity('journal-entry');
    journalEntryComponentsPage = new JournalEntryComponentsPage();
    await browser.wait(ec.visibilityOf(journalEntryComponentsPage.title), 5000);
    expect(await journalEntryComponentsPage.getTitle()).to.eq('bonGatewayApp.bonReplicaServiceJournalEntry.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(journalEntryComponentsPage.entities), ec.visibilityOf(journalEntryComponentsPage.noResult)),
      1000
    );
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
