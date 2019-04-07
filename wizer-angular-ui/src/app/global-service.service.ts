import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class GlobalServiceService {

  constructor(private httpClient: HttpClient) { }

  private pageTitle;

  private baseUrl = 'http://localhost:3456';

  public getpageTitle() {
    return this.pageTitle;
  }

  public setpageTitle(value) {
    this.pageTitle = value;
  }

  public getBaseUrl() {
    return this.baseUrl;
  }

  public getHttpOptions () {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    return httpOptions;
  }

}
