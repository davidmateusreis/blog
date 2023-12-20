import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { YourNintendoNewsHeaderComponent } from './components/your-nintendo-news-header/your-nintendo-news-header.component';
import { YourNintendoNewsNewsComponent } from './components/your-nintendo-news-news/your-nintendo-news-news.component';
import { YourNintendoNewsFooterComponent } from './components/your-nintendo-news-footer/your-nintendo-news-footer.component';

@NgModule({
  declarations: [
    AppComponent,
    YourNintendoNewsHeaderComponent,
    YourNintendoNewsNewsComponent,
    YourNintendoNewsFooterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
