package com.ibm.item.item.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.item.item.model.Product;
import com.ibm.item.item.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {


    @Override
    public List<Product> findAll(){
        return null;
    }

    @Override
    public Product findById(Long id){
        return null;
    }

    @Override
    public Product save(Product product){
        return null;
    }

    @Override
    public void delete(Long id){
        
    }
}
