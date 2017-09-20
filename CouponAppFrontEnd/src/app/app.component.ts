import { Component, OnInit } from '@angular/core';

import {CouponService} from './coupon.service';
import {Coupon} from './coupon.model';

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

    ngOnInit() {


      this.couponService.getCoupons()
      .subscribe(coupons =>
           {
              this.coupons = coupons;
              console.log(JSON.stringify(this.coupons));
          },
          (error) => console.log(error)
      );



        }




    onSubmit() {

      this.couponService.saveCoupon(this.newCoupon) .subscribe(newCoupon =>
           {
            this.newCoupon = newCoupon;
            this.coupons.push(newCoupon);
        },
        (error) => console.log(error)
    );

    console.log(JSON.stringify(this.coupons));

}
}
