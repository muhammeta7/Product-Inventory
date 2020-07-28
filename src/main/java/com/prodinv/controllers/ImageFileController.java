package com.prodinv.controllers;

import com.prodinv.exceptions.InvalidImageFileException;
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
import java.util.logging.Logger;
import java.util.logging.Level;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageFileController
{
    private ImageFileService service;
    private final static Logger logger = Logger.getLogger(ImageFileController.class.getName());

    @Autowired
    public ImageFileController(ImageFileService service)
    {
        this.service = service;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<ImageFile> uploadImage(@Valid @RequestPart("image") MultipartFile image) throws IOException
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

        return new ResponseEntity<>(upload, HttpStatus.CREATED);
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
