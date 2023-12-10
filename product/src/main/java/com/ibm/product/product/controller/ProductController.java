package com.ibm.product.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.product.product.model.Product;
import com.ibm.product.product.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private Environment env;

    @Autowired
    private ProductService productService;

    @PostMapping("/create")    
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        productService.deleteById(id);
    }

    @GetMapping("/find-product/{id}")
    public Product findProduct(@PathVariable Long id){
        Product product = productService.findById(id);
        product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        return product;
    }

    @GetMapping("/list-all")
    public List<Product> list(){
        return productService.findAll().stream().map(product -> {
            product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList());
    }
}
