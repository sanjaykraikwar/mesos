package com.epam.mesos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.mesos.controller.CouponController;
import com.epam.mesos.model.Coupon;
import com.epam.mesos.repository.CouponRepository;

public class CouponControllerTest {
	
	
	Coupon coupon ;
	
	CouponController controller;
	
	CouponRepository couponRepository ;
	
	Iterable<Coupon> itCoupon;
	
	Iterator  iterator;
	
	String uId ;
	
	 String couponTitle ;
	
	@Before
	public void setUp()  {
		
		/*mocking the required Object*/
		
		 coupon = mock(Coupon.class);
		
		 controller= new CouponController();
		
		 couponRepository =mock(CouponRepository.class);
		 
		 controller.setCouponRepository(couponRepository);
		 
		 itCoupon=mock(Iterable.class);
		 
		 iterator=mock(Iterator.class);
		 
		 uId="1de4bc8c-bb54-4b12-8a2a-ccbbd1f417a3";
		 
		 couponTitle="1de4bc8c-bb54-4b12-8a2a-ccbbd1f417a4";
	}

/* Test case for get Coupon based on ID*/
	@Test
	public void testGetCouponbyId() {



		// define return value for method getUniqueId()
		when(couponRepository.findOne(uId)).thenReturn(coupon);

		ResponseEntity<Coupon> couponResponse= controller.getCoupon(uId);

		// use mock in test....
		assertEquals(couponResponse.getBody().getId(), coupon.getId());
		assertEquals(couponResponse.getStatusCode(), HttpStatus.OK);
	}

	/* Test case for get Coupon based on ID null result*/
	@Test
	public void testGetNULLCouponbyId() {



		// define return value for method getUniqueId()
		when(couponRepository.findOne(uId)).thenReturn(null);

		ResponseEntity<Coupon> couponResponse= controller.getCoupon(uId);

		// use mock in test....
		assertEquals(couponResponse.getBody().getId(), null);
		assertEquals(couponResponse.getStatusCode(), HttpStatus.OK);
	}


	/* Test case for get Coupon based on couponTitle*/
	@Test
	public void testGetCouponbyTitle() {
	
		
		
		
        // define return value for method getUniqueId()
        when(couponRepository.getCouponByCouponTitle(couponTitle)).thenReturn(coupon);
        
        ResponseEntity<Coupon> couponResponse= controller.getCouponByTitle(couponTitle);
       

        // use mock in test....
        assertEquals(couponResponse.getBody().getCouponTitle(), coupon.getCouponTitle());
        assertEquals(couponResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testGetAllCoupon() {
		
	
        // define return value for method getUniqueId()
        when(couponRepository.findAll()).thenReturn(itCoupon);
        when(itCoupon.iterator()).thenReturn(iterator);
        
        ResponseEntity<List<Coupon>> couponResponse  = controller.getAllCoupons();

        // use mock in test....
      
       assertEquals(couponResponse.getStatusCode(), HttpStatus.OK);
       
	}
	
	
	@Test
	public void testSaveCoupon() {
		
		
        // define return value for method getUniqueId()
        when(couponRepository.getCouponByCouponTitle(couponTitle)).thenReturn(coupon);
        
        ResponseEntity<Coupon> couponResponse= controller.saveCoupon(coupon);

        // use mock in test....
        
        assertEquals(couponResponse.getStatusCode(), HttpStatus.CREATED);
	}
	
	@Test
	public void testUpdateCoupon() {
		
		
        // define return value for method getUniqueId()
      
        
        ResponseEntity<Coupon> couponResponse= controller.updateCoupon(uId, coupon);

        // use mock in test....
    
        assertEquals(couponResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testDeleteCoupon() {
		
		 
        ResponseEntity<Coupon> couponResponse= controller.deleteCoupon(uId);

        assertEquals(couponResponse.getStatusCode(), HttpStatus.NO_CONTENT);
	}



}
