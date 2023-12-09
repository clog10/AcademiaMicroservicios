package com.ibm.product.product.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.ibm.product.product.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
