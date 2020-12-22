import { Component, Input } from '@angular/core';
import { Maybe, CowVo } from 'app/bonpublicgraphql/bonpublicgraphql';

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
}
