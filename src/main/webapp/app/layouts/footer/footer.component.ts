import { Component } from '@angular/core';
import { VERSION } from 'app/app.constants';

@Component({
  selector: 'jhi-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
})
export class FooterComponent {
  today: number = Date.now();
  version: string;

  constructor() {
    this.version = VERSION ? (VERSION.toLowerCase().startsWith('v') ? VERSION : 'v' + VERSION) : '';
  }
}
