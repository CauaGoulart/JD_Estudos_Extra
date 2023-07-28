import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LazyPage2Component } from './lazy-page2.component';

const routes: Routes = [{ path: '', component: LazyPage2Component }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LazyPage2RoutingModule { }
