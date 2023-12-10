package com.ibm.item.item.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ibm.item.item.model.Product;

@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/product/create")
    public Product create(@RequestBody Product product);

    @DeleteMapping("/product/delete/{id}")
    public void delete(@PathVariable Long id);

    @GetMapping("/product/find-product/{id}")
    public Product findProduct(@PathVariable Long id);

    @GetMapping("/product/list-all")
    public List<Product> list();

}
