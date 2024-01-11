import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { YourNintendoNewsHeaderComponent } from './components/your-nintendo-news-header/your-nintendo-news-header.component';
import { YourNintendoNewsNewsComponent } from './components/your-nintendo-news-news/your-nintendo-news-news.component';
import { YourNintendoNewsFooterComponent } from './components/your-nintendo-news-footer/your-nintendo-news-footer.component';
import { DateAsAgoPipe } from './shared/date-as-ago.pipe';
import { YourNintendoNewsPaginatorComponent } from './components/your-nintendo-news-paginator/your-nintendo-news-paginator.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { YourNintendoNewsContactComponent } from './components/your-nintendo-news-contact/your-nintendo-news-contact.component';
import { YourNintendoNewsAboutComponent } from './components/your-nintendo-news-about/your-nintendo-news-about.component';
import { YourNintendoNewsNewsDetailsComponent } from './components/your-nintendo-news-news-details/your-nintendo-news-news-details.component';
import { YourNintendoNewsTermsComponent } from './components/your-nintendo-news-terms/your-nintendo-news-terms.component';

@NgModule({
  declarations: [
    AppComponent,
    YourNintendoNewsHeaderComponent,
    YourNintendoNewsNewsComponent,
    YourNintendoNewsFooterComponent,
    DateAsAgoPipe,
    YourNintendoNewsPaginatorComponent,
    YourNintendoNewsContactComponent,
    YourNintendoNewsAboutComponent,
    YourNintendoNewsNewsDetailsComponent,
    YourNintendoNewsTermsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
