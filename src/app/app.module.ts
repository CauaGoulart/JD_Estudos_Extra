import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LazyPageModule } from './lazy-page/lazy-page.module';

@NgModule({
  declarations: [
    AppComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LazyPageModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
