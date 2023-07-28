import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FruitListComponent } from './fruit-list/fruit-list.component';
import { CandyListComponent } from './candy-list/candy-list.component';
import { LazyPage3Component } from './lazy-page3/lazy-page3.component';


const routes: Routes = [
  {
    path: '',
    component: LazyPage3Component,
    children: [
      { path: '', redirectTo: 'fruits', pathMatch: 'full' },
      { path: 'fruits', component: FruitListComponent },
      { path: 'candies', component: CandyListComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LazyPage3RoutingModule { }
