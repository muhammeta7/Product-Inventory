package com.prodinv.controllers;

import com.prodinv.models.ImageFile;
import com.prodinv.services.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ImageFileController
{
    private ImageFileService service;

    @Autowired
    public ImageFileController(ImageFileService service)
    {
        this.service = service;
    }


}
