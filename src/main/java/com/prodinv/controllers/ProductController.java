package com.prodinv.controllers;

import com.prodinv.models.Product;
import com.prodinv.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<Iterable<Product>> index() {
        return new ResponseEntity<>(service.index(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return this.service.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/products/name")
    public ResponseEntity<?> findByName(@RequestParam String name) {
        return this.service.findByName(name)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/products/abbr")
    public ResponseEntity<?> findByAbbr(@RequestParam String abbr) {
        return this.service.findByAbbr(abbr)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/products/category")
    public ResponseEntity<Collection<Product>> findByCategory(@RequestParam String category) {
        return new ResponseEntity<>(service.findByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/products/category/index")
    public ResponseEntity<Collection<String>> findCategories() {
        return new ResponseEntity<>(service.listCategories(), HttpStatus.OK);
    }

    @GetMapping("/products/abbr/index")
    public ResponseEntity<Collection<String>> findAbbreviations() {
        return new ResponseEntity<>(service.listAbbreviations(), HttpStatus.OK);
    }

    @GetMapping("/products/location/index")
    public ResponseEntity<Collection<String>> findLocations() {
        return new ResponseEntity<>(service.listLocations(), HttpStatus.OK);
    }

    //    @PostMapping("/products")
//    public ResponseEntity<Product> create(@Valid @RequestPart("product") Product product,
//                                          @RequestPart(value = "image", required = false)MultipartFile image) throws IOException
    @PostMapping("/products")
    public ResponseEntity<Product> create(@Valid @ModelAttribute("product") Product product,
                                          @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return new ResponseEntity<>(service.create(product, image), HttpStatus.CREATED);
    }

    @PostMapping("/products/create")
    public ResponseEntity<Product> create(@Valid @ModelAttribute("product") Product product){
        return new ResponseEntity<>(service.create(product), HttpStatus.CREATED);
    }

//    @PostMapping("products/photo")
//    public ResponseEntity<Product> createProd(@Valid @RequestPart("product") Product product, @RequestPart("photo") MultipartFile file) throws IOException
//    {
//        return  new ResponseEntity<>(service.createProd(product, file), HttpStatus.OK);
//    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}
