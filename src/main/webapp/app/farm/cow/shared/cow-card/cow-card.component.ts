import { Component, Input } from '@angular/core';
import { CowVo, Visibility } from 'app/bonpublicgraphql/bonpublicgraphql';
import { AccountService } from 'app/core/auth/account.service';
import { Maybe } from 'graphql/jsutils/Maybe';

@Component({
  selector: 'jhi-cow-card',
  templateUrl: './cow-card.component.html',
  styleUrls: ['./cow-card.component.scss'],
})
export class CowCardComponent {
  @Input()
  cow?: Maybe<CowVo>;
  @Input()
  matri?: Maybe<CowVo>;
  @Input()
  patri?: Maybe<CowVo>;

  constructor(private accountService: AccountService) {}

  private enableParentLink(cow: Maybe<CowVo>): boolean {
    return this.accountService.isAuthenticated() || cow?.visibility === Visibility.RoleAnonymous;
  }

  enableMatriLink(): boolean {
    return this.enableParentLink(this.matri);
  }

  enablePatriLink(): boolean {
    return this.enableParentLink(this.patri);
  }
}
