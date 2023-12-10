package com.ibm.item.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.item.item.model.Product;
import com.ibm.item.item.service.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    @Qualifier("serviceRest")
    private ItemService itemService;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping("/list-all")
    public List<Product> list() {
        return itemService.findAll();
    }

    @CircuitBreaker(name = "items", fallbackMethod = "alternativeMethod")
    @GetMapping("/find/{id}")
    public Product findById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @CircuitBreaker(name = "items", fallbackMethod = "alternativeMethod2")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> detail(@PathVariable Long id){
        Product product = itemService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public Product info(@PathVariable Long id){
        return circuitBreakerFactory.create("items").run(() -> itemService.findById(id),
        e -> alternativeMethod(e));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return itemService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }

    public Product alternativeMethod(Throwable e) {
        Product product = new Product();
        product.setId(1L);
        product.setName("Default product");
        product.setPrice(0.0);
        return product;
    }

    public ResponseEntity<Product> alternativeMethod2(Throwable e) {
        Product product = new Product();
        product.setId(1L);
        product.setName("Default product");
        product.setPrice(0.0);
        return new ResponseEntity<>(product, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
