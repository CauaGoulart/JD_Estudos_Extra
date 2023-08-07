import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LazyPage2RoutingModule } from './lazy-page2-routing.module';
import { LazyPage2Component } from './lazy-page2.component';


@NgModule({
  declarations: [
    LazyPage2Component
  ],
  imports: [
    CommonModule,
    LazyPage2RoutingModule
  ]
})
export class LazyPage2Module { }
