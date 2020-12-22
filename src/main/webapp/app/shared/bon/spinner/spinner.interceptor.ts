import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { SpinnerService } from 'app/shared/bon/spinner/spinner.service';

@Injectable()
export class SpinnerInterceptor implements HttpInterceptor {
  constructor(private spinnerService: SpinnerService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.spinnerService.addRequest(request.url);
    return next.handle(request).pipe(
      tap(
        (evt: HttpEvent<any>) => {
          if (evt instanceof HttpResponse) {
            this.spinnerService.removeRequest(request.url);
          }
          return evt;
        },
        () => {
          this.spinnerService.removeRequest(request.url);
        }
      )
    );
  }
}
