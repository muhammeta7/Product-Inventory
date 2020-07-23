package com.prodinv.controllers;

import com.prodinv.models.ImageFile;
import com.prodinv.services.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
