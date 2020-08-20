package com.prodinv.controllers;

import com.prodinv.models.ImageFile;
import com.prodinv.models.Product;
import com.prodinv.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController
{
    private final ProductService service;

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

    @GetMapping("/products/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        return this.service.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/products/name")
    public ResponseEntity<?> findByName(@RequestParam String name)
    {
        return this.service.findByName(name)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/products/abbr")
    public ResponseEntity<?> findByAbbr(@RequestParam String abbr)
    {
        return this.service.findByAbbr(abbr)
                .map(product -> ResponseEntity.ok().body(product))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/products/category")
    public ResponseEntity<Collection<Product>> findByCategory(@RequestParam String category)
    {
        return new ResponseEntity<>(service.findByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/products/category/index")
    public ResponseEntity<Collection<String>> findCategories()
    {
        return new ResponseEntity<>(service.listCategories(), HttpStatus.OK);
    }

    @GetMapping("/products/abbr/index")
    public ResponseEntity<Collection<String>> findAbbreviations()
    {
        return new ResponseEntity<>(service.listAbbreviations(), HttpStatus.OK);
    }

    @GetMapping("/products/location/index")
    public ResponseEntity<Collection<String>> findLocations()
    {
        return new ResponseEntity<>(service.listLocations(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}/image")
    public ResponseEntity<Iterable<ImageFile>> findProductPhotos(@PathVariable Long id)
    {
        return new ResponseEntity<>(service.findPhotos(id), HttpStatus.OK);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<?> create(@Valid @RequestBody Product product)
    {
        return new ResponseEntity<>(service.create(product), HttpStatus.CREATED);
    }

    @PutMapping(value = "products/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, @PathVariable Long id)
    {
        return new ResponseEntity<>(service.update(id, product), HttpStatus.OK);
    }

    @PutMapping(value = "/products/{id}/upload_photo",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> attachPhoto(@Valid @PathVariable("id") Long id, @RequestPart("image") MultipartFile image) throws IOException
    {
        return new ResponseEntity<>(service.attachPhoto(id, image), HttpStatus.OK);
    }

    @PutMapping(value = "products/{id}/increase/qty")
    public ResponseEntity<Product> increaseQuantity(@PathVariable Long id, @RequestParam Integer qty){
        return new ResponseEntity<>(service.increaseQuantity(id, qty), HttpStatus.OK);
    }

    @PutMapping(value = "products/{id}/decrease/qty")
    public ResponseEntity<Product> decreaseQuantity(@PathVariable Long id, @RequestParam Integer qty){
        return new ResponseEntity<>(service.decreaseQuantity(id, qty), HttpStatus.OK);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        if(service.delete(id))
        {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(String.format("{\"message\":\"Successfully deleted product %d\"}", id));
        }
        else
        {
            return ResponseEntity
                    .noContent()
                    .build();
        }
    }
}
