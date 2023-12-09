package com.ibm.product.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.product.product.model.Product;
import com.ibm.product.product.model.repository.ProductRepository;
import com.ibm.product.product.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id){
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAll(){
        return (List<Product>) productRepository.findAll();
    }

}
