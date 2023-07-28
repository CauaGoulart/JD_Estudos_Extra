import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'lazy', loadChildren: () => import('./lazy-page/lazy-page.module').then(m => m.LazyPageModule) },
  { path: 'lazy2', loadChildren: () => import('./lazy-page2/lazy-page2.module').then(m => m.LazyPage2Module) },
  { path: 'lazy3', loadChildren: () => import('./lazy-page3/lazy-page3.module').then(m => m.LazyPage3Module) },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
