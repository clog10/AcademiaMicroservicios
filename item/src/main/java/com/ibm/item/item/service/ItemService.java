package com.ibm.item.item.service;

import java.util.List;

import com.ibm.item.item.model.Product;

public interface ItemService {

    public Product save(Product product);

    public void delete(Long id);

    public Product findById(Long id);

    public List<Product> findAll();

}
