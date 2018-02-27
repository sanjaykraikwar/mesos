import { Component, OnInit } from '@angular/core';

import {CouponService} from './coupon.service';
import {Coupon} from './coupon.model';
import {MyDatePicker} from 'mydatepicker/src/my-date-picker/index';
import {DatepickerModule} from 'ng2-datepicker-bootstrap';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {


 public newCoupon: Coupon;
  coupons: Coupon[] = [];

    constructor(private couponService: CouponService) {
      this.newCoupon = new Coupon();

    }

    select(coupon) {
      this.newCoupon = coupon;
    }

    ngOnInit() {
      this.getCoupons();

    }

  getCoupons()
      {

      this.couponService.getCoupons()
      .subscribe(coupons =>
           {
              this.coupons = coupons;
              console.log(JSON.stringify(this.coupons));
          },
          (error) => console.log(error)
      );



        }

    save() {

      this.couponService.saveCoupon(this.newCoupon) .subscribe(newCoupon =>
           {
            this.newCoupon = newCoupon;
            //this.coupons.push(newCoupon);
        },
        (error) => console.log(error)
    );

    this.getCoupons();
  }

    delete(coupon: Coupon) {

            this.couponService.deleteCoupon(this.newCoupon.id) .subscribe(newCoupon =>
                 {
                 // this.newCoupon = coupon;
                  this.newCoupon = new Coupon();
              },
              (error) => console.log(error)
          );
          this.getCoupons();
        }
          update(coupon: Coupon) {

            this.couponService.updateCoupon(this.newCoupon) .subscribe(newCoupon =>
              {
               this.newCoupon = coupon;
               this.coupons.push(coupon);
           },
           (error) => console.log(error)
       );

    console.log(JSON.stringify(this.coupons));
    this.getCoupons();
}
}
