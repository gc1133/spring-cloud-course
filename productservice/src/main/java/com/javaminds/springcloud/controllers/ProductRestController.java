package com.javaminds.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaminds.springcloud.model.Coupon;
import com.javaminds.springcloud.model.Product;
import com.javaminds.springcloud.repos.ProductRepo;
import com.javaminds.springcloud.restclients.CouponClient;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CouponClient couponClient;

	@PostMapping("/products")
	@Retry(name = "product-api", fallbackMethod = "handleError")
	public Product create(@RequestBody Product product) {
		Coupon coupon = couponClient.getCoupon(product.getCouponCode());
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return productRepo.save(product);
	}
	
	public Product handleError(Product product, Exception exception) {
		return product;
	}
	
}
