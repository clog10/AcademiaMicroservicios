package com.ibm.item.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.item.item.model.Product;
import com.ibm.item.item.service.ItemService;

@RestController
@RequestMapping("/item")
public class ProductController {

    @Autowired
    private ItemService itemService;

    @GetMapping("list-all")
    public List<Product> list(){
        return null;
    }

    @GetMapping("/find/{id}")
    public Product findById(@PathVariable Long id){
        return null;
    }

    @PostMapping("/create")
    public Product create(@RequestBody Product product){
        return null;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){

    }

}
