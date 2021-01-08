import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { AstridSharedModule } from 'app/shared/shared.module';
import { AstridCoreModule } from 'app/core/core.module';
import { AstridAppRoutingModule } from './app-routing.module';
import { AstridHomeModule } from './home/home.module';
import { AstridEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    AstridSharedModule,
    AstridCoreModule,
    AstridHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    AstridEntityModule,
    AstridAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class AstridAppModule {}
