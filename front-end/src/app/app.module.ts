import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserComponent } from './user/user.component';
import { StockComponent } from './stock/stock.component';
import { AppRoutingModule } from './app-routing.module';
import { NgZorroAntdModule } from './ng-zorro-antd.module';
import { ReactiveFormsModule} from '@angular/forms'
import { CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';
import { StorageService } from './store/storage.service'
import { DatePipe } from '@angular/common';

registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    StockComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NgZorroAntdModule,
    ReactiveFormsModule,
    CanvasJSAngularChartsModule,
    DatePipe
  ],
  providers: [
    StorageService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
