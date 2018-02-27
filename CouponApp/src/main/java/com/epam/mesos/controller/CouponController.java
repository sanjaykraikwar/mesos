package com.epam.mesos.controller;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.mesos.model.Coupon;
import com.epam.mesos.repository.CouponRepository;

@RestController
public class CouponController {

	static Logger logger = LoggerFactory.getLogger(CouponController.class);

	private CouponRepository couponRepository;

	@Autowired
	public void setCouponRepository(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}

	// -------------------Create a
	// Coupon--------------------------------------------------------
	
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/coupon", headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<Coupon> saveCoupon(@RequestBody Coupon coupon) {

		logger.info("Inside Save Coupon Service ");

		coupon.setId(UUID.randomUUID().toString());

		Coupon newCoupon = couponRepository.save(coupon);

		logger.info("Coupon Saved");

		return new ResponseEntity(newCoupon, HttpStatus.CREATED);
	}

	// -------------------Retrieve Coupon based on
	// Id--------------------------------------------------------

	@GetMapping(value = "/coupon/{id}", headers = "Accept=application/json", produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Coupon> getCoupon(@PathVariable("id") String id) {

		logger.info("Inside Get Coupon Service id=", id);

		Coupon coupon = couponRepository.findOne(id);

		if (coupon == null) {
			logger.info("Coupon with id ", id, " not found");
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		logger.info("Coupon retrieved");

		return new ResponseEntity(coupon, HttpStatus.OK);
	}

	// -------------------Retrieve Coupon based on
	// title--------------------------------------------------------

	@GetMapping(value = "/coupon", headers = "Accept=application/json", produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Coupon> getCouponByTitle(@RequestParam String title) {

		logger.info("Inside Get Coupon Service Title=", title);

		Coupon coupon = couponRepository.getCouponByCouponTitle(title);

		if (coupon == null) {
			logger.info("Coupon with id ", title, " not found");
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		logger.info("Coupon retrieved based on title");

		return new ResponseEntity(coupon, HttpStatus.OK);
	}

	// -------------------Retrieve all Coupons
	// --------------------------------------------------------

	@GetMapping(value = "/coupon/all", headers = "Accept=application/json", produces = "application/json")
	//@CrossOrigin(origins = "http://localhost:4200")
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<Coupon>> getAllCoupons() {

		logger.info("Inside Get All Coupon Service ");

		Iterable<Coupon> coupons = couponRepository.findAll();

		if (coupons == null) {
			logger.info("Coupons not found");
			return new ResponseEntity(HttpStatus.NOT_FOUND);

		} else {
			logger.info("all Coupon retrieved");

			List<Coupon> listCoupons = IteratorUtils.toList(coupons.iterator());

			return new ResponseEntity(listCoupons, HttpStatus.OK);
		}

	}

	// ------------------- Update a coupon
	// --------------------------------------------------------
	@CrossOrigin(origins = "*")
	@PutMapping(value = "/coupon/{id}", headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<Coupon> updateCoupon(@PathVariable("id") String id, @RequestBody Coupon coupon) {

		logger.info("Inside Update Coupon Service ");

		
		 Coupon newCoupon = couponRepository.findOne(id);
			if (coupon.getCouponTitle() != null)
				newCoupon.setCouponTitle(coupon.getCouponTitle());
			if (coupon.getCouponDescription() != null)
				newCoupon.setCouponDescription(coupon.getCouponDescription());
			if (coupon.getValue() != null && coupon.getValue() !=0)
				newCoupon.setValue(coupon.getValue());
			if (coupon.getCreditType() != null && coupon.getCreditType() !=0)
				newCoupon.setCreditType(coupon.getCreditType());
			if (coupon.getAuthor() != null)
				newCoupon.setAuthor(coupon.getAuthor());
			if (coupon.getValidTill() != null)
				newCoupon.setValidTill(coupon.getValidTill());
			if (coupon.getCreatedDate() != null)
				newCoupon.setCreatedDate(coupon.getCreatedDate());
			if (coupon.getUpdatedDate() != null)
				newCoupon.setUpdatedDate(coupon.getUpdatedDate());
			newCoupon = couponRepository.save(newCoupon);
		

		logger.info("Coupon Successfully Updated ");

		return new ResponseEntity(newCoupon, HttpStatus.OK);
	}

	// ------------------- Delete a coupon
	// --------------------------------------------------------
	@CrossOrigin(origins = "*")
	@DeleteMapping("/coupon/{id}")
	public ResponseEntity<Coupon> deleteCoupon(@PathVariable("id") String id) {
		logger.info("Inside Delete Coupon Service ");

		couponRepository.delete(id);
		logger.info("Coupon Successfully Deleted");
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
