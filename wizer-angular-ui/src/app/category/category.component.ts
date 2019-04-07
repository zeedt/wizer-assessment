import { Component, OnInit } from '@angular/core';
import {GlobalServiceService} from '../global-service.service';
import {tap} from 'rxjs/internal/operators';
import {HttpClient} from '@angular/common/http';
import {Category} from '../models/Category';
import {BookSearchModel} from '../models/BookSearchModel';
import {Book} from '../models/Book';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  public allCategoriesUrl: string;
  public addNewCategoryUrl: string;
  public registrationMessage: string;
  public categories : any;
  public categoryRequest : Category;

  constructor(private globalService: GlobalServiceService, private httpClient:HttpClient) { }

  ngOnInit() {
    this.globalService.setpageTitle('Category');
    this.allCategoriesUrl = this.globalService.getBaseUrl() + "/api/category/all";
    this.addNewCategoryUrl = this.globalService.getBaseUrl() + "/api/category/new";
    this.categoryRequest = new Category();
    this.fetchAllCategories();
  }

  fetchAllCategories() {
    this.httpClient.get(this.allCategoriesUrl,this.globalService.getHttpOptions())
      .subscribe(
        result=> {
          this.categories = result;
        },
        error => {
          console.log("Error while fetching all categories due to " + JSON.stringify(error));
          this.categories = [];
        }
      );
  }

  addNewCategory() {
    console.log(JSON.stringify(this.categoryRequest));
    this.httpClient.post(this.addNewCategoryUrl, this.categoryRequest, this.globalService.getHttpOptions())
      .pipe(
        tap(
          data => {
            console.log("Successfully added Category");
            this.registrationMessage = "Operation successful";
          },
          error => {
            this.registrationMessage = "Operation Not successful : " + error.error.message;
            console.log("Error occurred while adding category");
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
    this.categoryRequest = new Category();
    this.fetchAllCategories();
  }


}
