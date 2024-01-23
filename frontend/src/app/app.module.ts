import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './components/header/header.component';
import { NewsComponent } from './components/news/news.component';
import { FooterComponent } from './components/footer/footer.component';
import { DateAsAgoPipe } from './shared/date-as-ago.pipe';
import { PaginatorComponent } from './components/paginator/paginator.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { ContactComponent } from './components/contact/contact.component';
import { AboutComponent } from './components/about/about.component';
import { NewsDetailsComponent } from './components/news-details/news-details.component';
import { TermsComponent } from './components/terms/terms.component';
import { PrivacyComponent } from './components/privacy/privacy.component';
import { DisqusComponent } from './components/disqus/disqus.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NewsComponent,
    FooterComponent,
    DateAsAgoPipe,
    PaginatorComponent,
    ContactComponent,
    AboutComponent,
    NewsDetailsComponent,
    TermsComponent,
    PrivacyComponent,
    DisqusComponent
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
