import { Directive, ElementRef, Input, Renderer2, OnChanges } from '@angular/core';
import { Visibility } from 'app/bonpublicgraphql/bonpublicgraphql';

@Directive({
  selector: '[jhiBonVisibilityClass]',
})
export class BonVisibilityClassDirective implements OnChanges {
  @Input('jhiBonVisibilityClass')
  visibility?: Visibility | string | null;

  constructor(private renderer: Renderer2, private el: ElementRef) {}

  ngOnChanges(): void {
    if (this.visibility) {
      const classArray = [];
      if (this.visibility === Visibility.RoleAnonymous) {
        classArray.push('is-role-anonymous');
      } else if (this.visibility === Visibility.RoleUser) {
        classArray.push('is-role-user');
      } else if (this.visibility === Visibility.RoleAdmin) {
        classArray.push('is-role-admin');
      }
      this.renderer.addClass(this.el.nativeElement, classArray.join(' '));
    }
  }
}
