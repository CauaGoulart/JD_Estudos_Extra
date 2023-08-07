import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FruitListComponent } from './fruit-list/fruit-list.component';
import { CandyListComponent } from './candy-list/candy-list.component';
import { LazyPage3Component } from './lazy-page3/lazy-page3.component';
import { LazyPage3RoutingModule } from './lazy-page3-routing.module';



@NgModule({
  declarations: [
    FruitListComponent,
    CandyListComponent,
    LazyPage3Component
  ],
  imports: [
    CommonModule,
    LazyPage3RoutingModule
  ]
})
export class LazyPage3Module { }
