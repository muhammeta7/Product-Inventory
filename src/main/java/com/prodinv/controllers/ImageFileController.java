package com.prodinv.controllers;

import com.prodinv.models.ImageFile;
import com.prodinv.services.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;


@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageFileController
{
    private ImageFileService service;

    @Autowired
    public ImageFileController(ImageFileService service)
    {
        this.service = service;
    }

    @PostMapping(value = "/upload")
    // consumes = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, "image/heic" })
    // TODO: Add proper consume = "" values
    public ResponseEntity<ImageFile> uploadImage(@Valid @RequestParam("image") MultipartFile image) throws IOException
    {
        // Do we really want to pass this sort of ResponseEntity?  Passing the image back as JSON seems inefficient
        return new ResponseEntity<>(service.uploadImage(image), HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ImageFile> findImageById(@PathVariable Long id) throws FileNotFoundException
//    {
//        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
//    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<byte[]> findImageByName(@PathVariable String name) throws FileNotFoundException
    {
        ImageFile img = service.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(img.getType()))
                .body(img.getImgBytes());
    }
}
