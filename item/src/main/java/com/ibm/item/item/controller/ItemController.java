package com.ibm.item.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    @Qualifier("serviceRest")
    private ItemService itemService;

    @GetMapping("/list-all")
    public List<Product> list(){
        return itemService.findAll();
    }

    @GetMapping("/find/{id}")
    public Product findById(@PathVariable Long id){
        return itemService.findById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        return itemService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        itemService.delete(id);
    }

}
