import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SpinnerService {
  spinnerSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  requestMap: Map<string, number> = new Map<string, number>();

  constructor() {}

  addRequest(url: string): void {
    if (!url) {
      console.warn('URL is missing');
      return;
    } else if (!url.includes('api/') && !url.includes('graphql')) {
      return;
    }
    const n = this.requestMap.get(url) || 0;
    this.requestMap.set(url, n + 1);
    this.spinnerSubject.next(true);
  }

  removeRequest(url: string): void {
    if (!url) {
      console.warn('URL is missing');
      return;
    }
    const n = this.requestMap.get(url) || 0;
    if (n > 1) {
      this.requestMap.set(url, n - 1);
    } else {
      this.requestMap.delete(url);
    }

    if (this.requestMap.size === 0) {
      this.spinnerSubject.next(false);
    }
  }
}
