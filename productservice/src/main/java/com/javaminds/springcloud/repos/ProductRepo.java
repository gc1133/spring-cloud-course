package com.javaminds.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaminds.springcloud.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
