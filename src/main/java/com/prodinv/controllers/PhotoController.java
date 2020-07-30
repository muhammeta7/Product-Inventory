package com.prodinv.controllers;

import com.prodinv.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(path = "api/photos")
public class PhotoController {


    private PhotoRepository imageRepository;

    @Autowired
    public PhotoController(PhotoRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


}
