package com.prodinv.controllers;

import com.prodinv.exceptions.InvalidImageFileException;
import com.prodinv.models.ImageFile;
import com.prodinv.services.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;
import java.util.logging.Level;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageFileController
{
    private final ImageFileService service;
    private final static Logger logger = Logger.getLogger(ImageFileController.class.getName());

    @Autowired
    public ImageFileController(ImageFileService service)
    {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@Valid @RequestPart("image") MultipartFile image) throws IOException
    {
        ImageFile upload;

        try
        {
            upload = service.uploadImage(image);
        }
        catch(IOException e)
        {
            logger.log(Level.WARNING, "Attempted to upload a non-image file: " + image.getOriginalFilename() + ", Type: " + image.getContentType());
            throw new InvalidImageFileException(e.getMessage(), e.getCause());
        }

        URI newImageUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{name}")
                .buildAndExpand(upload.getFileName())
                .toUri();

        return new ResponseEntity<>(newImageUri, HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ImageFile> findImageById(@PathVariable Long id) throws FileNotFoundException
//    {
//        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
//    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<byte[]> findImageByName(@PathVariable String name) throws FileNotFoundException
    {

        return this.service.findByName(name)
                .map(img -> ResponseEntity.ok()
                        .contentType(MediaType.valueOf(img.getType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                        .body(img.getImgBytes()))
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }
}
