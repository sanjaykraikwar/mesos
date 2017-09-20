import {Http, Response, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';
import {Observable} from 'rxjs/Observable';
import {Coupon} from './coupon.model';
import {EventEmitter, Injectable, Inject, forwardRef} from '@angular/core';




@Injectable()
export class CouponService {


    constructor(private http: Http  ) {}

    getCoupon(coupon: Coupon): Observable<Coupon> {
        return this.http.get('http://localhost:8080/coupon/1de4bc8c-bb54-4b12-8a2a-ccbbd1f417a3')
        .map(res =>  res.json());
    }

    public getCoupons (): Observable<Coupon[]> {
      return this.http.get('http://localhost:8080/coupon/all')
                      .map(res => res.json());
      }


  saveCoupon(coupon: Coupon): Observable<Coupon> {



          let url = 'http://localhost:8080/coupon';

          let headers = new Headers({ 'Content-Type': 'application/json' });
          let options = new RequestOptions({ headers: headers });
          return this.http.post(url, coupon, options)
          .map(res =>  res.json());
      }

}
