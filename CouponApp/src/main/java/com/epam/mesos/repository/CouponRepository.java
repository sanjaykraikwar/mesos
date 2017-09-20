package com.epam.mesos.repository;



import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.epam.mesos.model.Coupon;



public interface CouponRepository extends CouchbaseRepository<Coupon, String> {

	Coupon getCouponByCouponTitle(String couponTitle);
	
	/*  @Override*/
	  @View(designDocument = "dev_mesos", viewName = "all")
	  Iterable<Coupon> findAll();


}
