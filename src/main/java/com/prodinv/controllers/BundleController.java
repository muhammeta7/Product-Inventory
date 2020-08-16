package com.prodinv.controllers;

import com.prodinv.models.Bundle;
import com.prodinv.services.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bundle>> findById(@PathVariable Long id)
    {
        return new ResponseEntity<>(bundleService.findById(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{bundleId}/add")
    public ResponseEntity<Bundle> addToBundle(@Valid @PathVariable Long bundleId, @RequestParam(name = "product") Long productId,
                                                   @RequestParam(name = "qty") Integer qty)
    {
        return new ResponseEntity<>(bundleService.add(bundleId, productId, qty), HttpStatus.OK);
    }
}
