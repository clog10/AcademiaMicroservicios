package com.ibm.product.product.service;

import com.ibm.product.product.model.Product;
import java.util.List;

public interface ProductService {

    public Product save(Product product);
    public void deleteById(Long id);
    public Product findById(Long id);
    public List<Product> findAll();

}
