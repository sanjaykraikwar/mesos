import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {DatePickerModule} from 'ng2-datepicker-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import {CouponService} from './coupon.service';

import { AppComponent } from './app.component';



@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, FormsModule, DatePickerModule,
    HttpModule,

  ],
  providers: [CouponService],
  bootstrap: [AppComponent]
})
export class AppModule { }
