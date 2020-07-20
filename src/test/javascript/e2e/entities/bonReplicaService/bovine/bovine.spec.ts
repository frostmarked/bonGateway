import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { BovineComponentsPage } from './bovine.page-object';

const expect = chai.expect;

describe('Bovine e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let bovineComponentsPage: BovineComponentsPage;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Bovines', async () => {
    await navBarPage.goToEntity('bovine');
    bovineComponentsPage = new BovineComponentsPage();
    await browser.wait(ec.visibilityOf(bovineComponentsPage.title), 5000);
    expect(await bovineComponentsPage.getTitle()).to.eq('bonGatewayApp.bonReplicaServiceBovine.home.title');
    await browser.wait(ec.or(ec.visibilityOf(bovineComponentsPage.entities), ec.visibilityOf(bovineComponentsPage.noResult)), 1000);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
