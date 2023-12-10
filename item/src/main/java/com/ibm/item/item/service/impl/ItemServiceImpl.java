package com.ibm.item.item.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.item.item.model.Product;
import com.ibm.item.item.service.ItemService;

@Service("serviceRest")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate clientRest;

    private final String path ="http://product-service/product/";

    @Override
    public List<Product> findAll(){
        List<Product> products = Arrays.asList(clientRest.getForObject(path.concat("list-all"), Product[].class));
        return products;
    }

    @Override
    public Product findById(Long id){
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        Product product = clientRest.getForObject(path.concat("find-product/{id}"), Product.class, pathVariables);
        return product;
    }

    @Override
    public Product save(Product product){
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response = clientRest.exchange(path.concat("create"), HttpMethod.POST, body, Product.class);
        Product productResponse = response.getBody();
        return productResponse;
    }

    @Override
    public void delete(Long id){
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        clientRest.delete(path.concat("delete/{id}"), pathVariables);
    }
}
