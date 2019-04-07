import { Component, OnInit } from '@angular/core';
import {GlobalServiceService} from '../global-service.service';
import {EChartOption} from 'echarts';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  public bookPerCategoryChartOption: EChartOption;
  public bookPerAuthorChartOption: EChartOption;
  public fetchBookPerCategoryDashboardDetailsUrl: string;
  public fetchBookPerAuthorDashboardDetailsUrl: string;

  constructor(public globalService: GlobalServiceService, public httpClient:HttpClient) { }

  ngOnInit() {
    this.globalService.setpageTitle('Dashboard');
    this.fetchBookPerCategoryDashboardDetailsUrl = this.globalService.getBaseUrl() + "/api/book/get-dashboard-details-for-books-per-category";
    this.fetchBookPerAuthorDashboardDetailsUrl = this.globalService.getBaseUrl() + "/api/book/get-dashboard-details-for-books-per-author";
    this.loadBookPerCategoryDashboardDetails();
    this.loadBookPerAuthorDashboardDetails();
  }

  loadBookPerCategoryDashboardDetails() {
    this.httpClient.get(this.fetchBookPerCategoryDashboardDetailsUrl)
      .subscribe(
        (result: any)=> {
          this.bookPerCategoryChartOption = {
            xAxis: {
              type: 'category',
              name : 'Category',
              data: result.xaxisData
            },
            yAxis: {
              type: 'value',
              name : 'No. of Books',
            },
            series: [{
              data: result.yaxisData,
              type: 'bar'
            }]
          }


        }
      );
  }

  loadBookPerAuthorDashboardDetails() {
    this.httpClient.get(this.fetchBookPerAuthorDashboardDetailsUrl)
      .subscribe(
        (result: any)=> {
          this.bookPerAuthorChartOption = {
            xAxis: {
              type: 'category',
              name : 'Author',
              data: result.xaxisData
            },
            yAxis: {
              type: 'value',
              name : 'No. of Books'
            },
            series: [{
              data: result.yaxisData,
              type: 'bar'
            }]
          }


        }
      );
  }

}
