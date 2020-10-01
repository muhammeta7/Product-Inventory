package com.prodinv.controllers;

import com.prodinv.models.Bundle;
import com.prodinv.services.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/bundle")
public class BundleController
{
    private BundleService bundleService;

    @Autowired
    public BundleController(BundleService bundleService)
    {
        this.bundleService = bundleService;
    }

    @PostMapping
    public ResponseEntity<?> createBundle(@Valid @RequestBody Bundle bundle)
    {
        return new ResponseEntity<>(bundleService.create(bundle), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Bundle>> findAll()
    {
        return new ResponseEntity<>(bundleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bundle>> findById(@PathVariable Long id)
    {
        return new ResponseEntity<>(bundleService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{bundleId}/add")
    public ResponseEntity<Bundle> addToBundle(@PathVariable Long bundleId, @RequestParam(name = "product_id") Long productId,
                                                   @RequestParam(name = "qty") Integer qty)
    {
        return new ResponseEntity<>(bundleService.add(bundleId, productId, qty), HttpStatus.OK);
    }

    @PutMapping("/{bundleId}/remove")
    public ResponseEntity<Bundle> removeFromBundle(@PathVariable Long bundleId,
                                                   @RequestParam(name = "product_id") Long productId)
    {
        return new ResponseEntity<>(bundleService.remove(bundleId, productId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        if(bundleService.delete(id))
        {
            return ResponseEntity.ok()
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
