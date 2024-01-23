import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { NewsComponent } from './components/news/news.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutComponent } from './components/about/about.component';
import { NewsDetailsComponent } from './components/news-details/news-details.component';
import { TermsComponent } from './components/terms/terms.component';
import { PrivacyComponent } from './components/privacy/privacy.component';

const routes: Routes = [
  { path: '', component: NewsComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'about', component: AboutComponent },
  { path: 'news/:year/:month/:slug', component: NewsDetailsComponent },
  { path: 'terms', component: TermsComponent },
  { path: 'privacy', component: PrivacyComponent },
  { path: 'page/:pageNumber', component: NewsComponent },
  { path: '**', redirectTo: '/page/1' }
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
