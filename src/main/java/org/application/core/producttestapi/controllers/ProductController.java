package org.application.core.producttestapi.controllers;

import org.application.core.producttestapi.entities.Product;
import org.application.core.producttestapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository repository;

   @GetMapping("/products/")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id){
        return repository.findById(id).get();
    }

    @PostMapping("/products/")
    public Product createProduct(@RequestBody Product product){
        return repository.save(product);
    }

    @PutMapping("/products/")
    public Product updateProduct(@RequestBody Product product){
        return repository.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") int id){
       repository.deleteById(id);
    }
}
