import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { BlupComponentsPage } from './blup.page-object';

const expect = chai.expect;

describe('Blup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let blupComponentsPage: BlupComponentsPage;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Blups', async () => {
    await navBarPage.goToEntity('blup');
    blupComponentsPage = new BlupComponentsPage();
    await browser.wait(ec.visibilityOf(blupComponentsPage.title), 5000);
    expect(await blupComponentsPage.getTitle()).to.eq('bonGatewayApp.bonReplicaServiceBlup.home.title');
    await browser.wait(ec.or(ec.visibilityOf(blupComponentsPage.entities), ec.visibilityOf(blupComponentsPage.noResult)), 1000);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
