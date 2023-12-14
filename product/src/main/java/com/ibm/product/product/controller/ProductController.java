package com.ibm.product.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.product.product.model.Product;
import com.ibm.product.product.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private Environment env;

    @Autowired
    private ProductService productService;

    @Value("${configuration.text}")
    String text;

    @Value("${configuration.service.name}")
    String nameEnvironment;

    @Value("${configuration.author.name}")
    String author;

    @Value("${configuration.author.email}")
    String email;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)    
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) 
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

    @GetMapping("/config")
    public ResponseEntity<?> getConfig() {
        Map<String, String> json = new HashMap<String, String>();
        json.put("name", nameEnvironment);
        json.put("description", text);
        json.put("author", author);
        json.put("email", email);
        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }
    
}
