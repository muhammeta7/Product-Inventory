package com.prodinv.controllers;

import com.prodinv.models.Product;
import com.prodinv.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController
{
    private ProductService service;

    @Autowired
    public ProductController(ProductService service)
    {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<Iterable<Product>> index()
    {
        return new ResponseEntity<>(service.index(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> create(@RequestBody Product product)
    {
        return new ResponseEntity<>(service.create(product), HttpStatus.CREATED);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id)
    {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}
