import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { YourNintendoNewsNewsComponent } from './components/your-nintendo-news-news/your-nintendo-news-news.component';
import { YourNintendoNewsContactComponent } from './components/your-nintendo-news-contact/your-nintendo-news-contact.component';
import { YourNintendoNewsAboutComponent } from './components/your-nintendo-news-about/your-nintendo-news-about.component';
import { YourNintendoNewsNewsDetailsComponent } from './components/your-nintendo-news-news-details/your-nintendo-news-news-details.component';
import { YourNintendoNewsTermsComponent } from './components/your-nintendo-news-terms/your-nintendo-news-terms.component';
import { YourNintendoNewsPrivacyComponent } from './components/your-nintendo-news-privacy/your-nintendo-news-privacy.component';

const routes: Routes = [
  { path: '', component: YourNintendoNewsNewsComponent },
  { path: 'contact', component: YourNintendoNewsContactComponent },
  { path: 'about', component: YourNintendoNewsAboutComponent },
  { path: 'news/:year/:month/:slug', component: YourNintendoNewsNewsDetailsComponent },
  { path: 'terms', component: YourNintendoNewsTermsComponent },
  { path: 'privacy', component: YourNintendoNewsPrivacyComponent },
  { path: 'page/:pageNumber', component: YourNintendoNewsNewsComponent }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes, {
      scrollPositionRestoration: 'enabled',
    })
  ],
  exports: [RouterModule],
})
export class AppRoutingModule { }
