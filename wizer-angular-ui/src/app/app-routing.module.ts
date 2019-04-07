import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {CategoryComponent} from './category/category.component';
import {BookComponent} from './book/book.component';
import {GlobalServiceService} from './global-service.service';

const routes: Routes = [
  {
    path : 'dashboard',
    component : DashboardComponent
  },
  {
    path : 'category',
    component : CategoryComponent
  },
  {
    path : 'book',
    component : BookComponent
  },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers : [GlobalServiceService]
})
export class AppRoutingModule { }
