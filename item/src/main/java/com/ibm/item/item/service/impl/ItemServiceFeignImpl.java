package com.ibm.item.item.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.item.item.client.ProductClient;
import com.ibm.item.item.model.Product;
import com.ibm.item.item.service.ItemService;

@Service("serviceFeign")
public class ItemServiceFeignImpl implements ItemService {

    @Autowired
    private ProductClient clientFeign;

    @Override
    public Product save(Product product) {
       return clientFeign.create(product);
    }

    @Override
    public void delete(Long id) {
        clientFeign.delete(id);
    }

    @Override
    public Product findById(Long id) {
        return clientFeign.findProduct(id);
    }

    @Override
    public List<Product> findAll() {
        return clientFeign.list();
    }

}
