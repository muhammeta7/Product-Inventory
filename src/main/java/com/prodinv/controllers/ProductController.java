package com.prodinv.controllers;

import com.prodinv.models.Product;
import com.prodinv.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
