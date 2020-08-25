import { Directive, ElementRef, Input, Renderer2, OnChanges } from '@angular/core';
import { PictureVo, Maybe } from '../../bonpublicgraphql/bonpublicgraphql';
import { pickPictureSourceUrl } from 'app/shared/bon/picturevo-util';

@Directive({
  selector: '[jhiCowPicture]',
})
export class CowPictureDirective implements OnChanges {
  @Input('jhiCowPicture')
  picture?: Maybe<PictureVo>;
  @Input()
  targetWidth?: string;

  constructor(private renderer: Renderer2, private el: ElementRef) {}

  ngOnChanges(): void {
    if (this.picture?.sources) {
      const tw = this.targetWidth ? parseInt(this.targetWidth, 10) || 992 : 992;
      const imgSrc = pickPictureSourceUrl(this.picture.sources, tw);
      this.renderer.setAttribute(this.el.nativeElement, 'src', imgSrc);

      const imgSrcSet = this.picture?.sources
        .filter(ps => ps && ps.url !== imgSrc)
        .map(ps => `${ps!.url} ${ps!.width}w`)
        .join(',');
      this.renderer.setAttribute(this.el.nativeElement, 'srcset', imgSrcSet);
    }
  }
}
