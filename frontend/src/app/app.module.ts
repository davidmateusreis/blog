import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { YourNintendoNewsHeaderComponent } from './components/your-nintendo-news-header/your-nintendo-news-header.component';
import { YourNintendoNewsNewsComponent } from './components/your-nintendo-news-news/your-nintendo-news-news.component';
import { YourNintendoNewsFooterComponent } from './components/your-nintendo-news-footer/your-nintendo-news-footer.component';
import { DateAsAgoPipe } from './shared/date-as-ago.pipe';
import { YourNintendoNewsPaginatorComponent } from './components/your-nintendo-news-paginator/your-nintendo-news-paginator.component';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    YourNintendoNewsHeaderComponent,
    YourNintendoNewsNewsComponent,
    YourNintendoNewsFooterComponent,
    DateAsAgoPipe,
    YourNintendoNewsPaginatorComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
