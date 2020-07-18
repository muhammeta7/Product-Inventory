package com.prodinv.controllers;

import com.prodinv.models.Product;
import com.prodinv.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @GetMapping("/products/name")
    public ResponseEntity<Product> findByName(@RequestParam String name)
    {
        return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/products/abbr")
    public ResponseEntity<Product> findByAbbr(@RequestParam String abbr)
    {
        return new ResponseEntity<>(service.findByAbbr(abbr), HttpStatus.OK);
    }

    @GetMapping("/products/category")
    public ResponseEntity<Collection<Product>> findByCategory(@RequestParam String category)
    {
        return new ResponseEntity<>(service.findByCategory(category), HttpStatus.OK);
    }

    @PostMapping("/products")
    @Validated
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
