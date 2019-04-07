import { Component, OnInit } from '@angular/core';
import {GlobalServiceService} from '../global-service.service';
import {BookSearchModel} from '../models/BookSearchModel';
import {HttpClient} from '@angular/common/http';
import {tap} from 'rxjs/internal/operators';
import {PaginationBaseModel} from '../models/PaginationBaseModel';
import {Book} from '../models/Book';
import {FormControl} from '@angular/forms';
import {Category} from '../models/Category';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  public bookSearchModel: BookSearchModel;
  public fetchPagedBooksUrl: string;
  public fetchCategoriesUrl: string;
  public addNewBookUrl: string;
  public loadingData : boolean;
  public paginationBaseModel : any;
  public categorySearchValue: FormControl;
  public isbnSearchValue: FormControl;
  public authorSearchValue: FormControl;
  public titleSearchValue: FormControl;
  public categories:any;
  public bookRequest:Book;
  public registrationMessage:string;

  constructor(public globalService: GlobalServiceService, public httpClient:HttpClient) {
    this.bookSearchModel = new BookSearchModel();
    this.bookRequest = new Book();
  }


  ngOnInit() {
    this.loadingData = true;
    this.globalService.setpageTitle('Book Management');
    this.fetchPagedBooksUrl = this.globalService.getBaseUrl() + "/api/book/get-page-with-parameters";
    this.fetchCategoriesUrl = this.globalService.getBaseUrl() + "/api/category/all";
    this.addNewBookUrl = this.globalService.getBaseUrl() + "/api/book/new";
    this.categorySearchValue = new FormControl('');
    this.authorSearchValue = new FormControl('');
    this.isbnSearchValue = new FormControl('');
    this.titleSearchValue = new FormControl('');
    this.loadBooksByPage();
    this.loadAllCategories();
  }

  fetchNext() {
    if ((this.bookSearchModel.pageNo+1) < this.paginationBaseModel.totalPages) {
      this.bookSearchModel.pageNo++;
      this.addFilterValuesToRequestModel();
      this.loadBooksByPage();
    }
  }

  fetchPrevious() {
    if (this.bookSearchModel.pageNo > 0) {
      this.bookSearchModel.pageNo--;
      this.addFilterValuesToRequestModel();
      this.loadBooksByPage();
    }
  }

  loadBooksByPage() {
    this.httpClient.post(this.fetchPagedBooksUrl, this.bookSearchModel, this.globalService.getHttpOptions())
      .pipe(
        tap(
          data => {
            console.log("data is " + JSON.stringify(data));
            this.paginationBaseModel = data;
            this.loadingData = false;
          },
          error => {
            console.log("Error gotten is " + JSON.stringify(error));
          }
        )
      )
      .subscribe(
        result=> {
        }
      );
  }

  loadAllCategories() {
    this.httpClient.get(this.fetchCategoriesUrl)
      .subscribe(
        result=> {
          this.categories = result;
          this.loadingData = false;
        }
      );
  }

  searchwithParams() {
    this.bookSearchModel.pageNo=0;
    this.bookSearchModel.pageSize=10;
    this.addFilterValuesToRequestModel();
    this.loadBooksByPage();
  }

  addFilterValuesToRequestModel() {
    this.bookSearchModel.title = this.titleSearchValue.value;
    this.bookSearchModel.author = this.authorSearchValue.value;
    this.bookSearchModel.categoryName = this.categorySearchValue.value;
    this.bookSearchModel.isbn = this.isbnSearchValue.value;
  }

  addNewBook() {
    console.log(JSON.stringify(this.bookRequest));
    this.httpClient.post(this.addNewBookUrl, this.bookRequest, this.globalService.getHttpOptions())
      .pipe(
        tap(
          data => {
            console.log("Successfully added book");
            this.registrationMessage = "Operation successful";
          },
          error => {
            this.registrationMessage = "Operation Not successful : " + error.error.message;
            console.log("Error occurred while adding book");
          }
        )
      )
      .subscribe(
        result=> {
        }
      );
  }

  resetModal(){
    this.registrationMessage = "";
    this.bookRequest = new Book();
    this.bookSearchModel = new BookSearchModel();
    this.loadBooksByPage();
  }

}
