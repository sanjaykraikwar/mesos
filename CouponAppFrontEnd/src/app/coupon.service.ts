import {Http, Response, Headers, RequestOptions} from '@angular/http';
import 'rxjs/Rx';
import {Observable} from 'rxjs/Observable';
import {Coupon} from './coupon.model';
import {EventEmitter, Injectable, Inject, forwardRef} from '@angular/core';




@Injectable()
export class CouponService {


    constructor(private http: Http  ) {}

    getCoupon(coupon: Coupon): Observable<Coupon> {

      let url = 'http://10.71.13.3:8081/coupon/' + coupon.id;
        return this.http.get(url)
        .map(res =>  res.json());
    }

    public getCoupons (): Observable<Coupon[]> {
      return this.http.get('http://10.71.13.3:8081/coupon/all')
                      .map(res => res.json());
      }


  saveCoupon(coupon: Coupon): Observable<Coupon> {



          let url = 'http://10.71.13.3:8081/coupon';

          let headers = new Headers({ 'Content-Type': 'application/json' });
          let options = new RequestOptions({ headers: headers });
          return this.http.post(url, coupon, options)
          .map(res =>  res.json());


      }



      deleteCoupon(id: string): Observable<any> {

              let url = 'http://10.71.13.3:8081/coupon/' + id ;
                return this.http.delete(url)
                .map(res =>  res.json());

            }


            updateCoupon(coupon: Coupon): Observable<Coupon> {

                        let url = 'http://10.71.13.3:8081/coupon/' + coupon.id;

                        let headers = new Headers({ 'Content-Type': 'application/json' });
                        let options = new RequestOptions({ headers: headers });
                        return this.http.put(url, coupon, options)
                        .map(res =>  res.json());

                  }


}
