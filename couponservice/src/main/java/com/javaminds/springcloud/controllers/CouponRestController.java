package com.javaminds.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaminds.springcloud.model.Coupon;
import com.javaminds.springcloud.repos.CouponRepo;

@RestController
@EnableEurekaClient
@RequestMapping("/couponapi")
public class CouponRestController {
	
	@Autowired
	CouponRepo couponRepo;
	
	@PostMapping("/coupons")
	public Coupon create(@RequestBody Coupon coupon) {
		return couponRepo.save(coupon);
	}
	
	@GetMapping("/coupons/{code}")
	public Coupon getCoupon(@PathVariable("code") String code) {
		System.out.println("Server 2");
		return couponRepo.findByCode(code);
	}

}
