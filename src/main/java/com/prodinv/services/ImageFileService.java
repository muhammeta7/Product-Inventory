package com.prodinv.services;

import com.prodinv.models.ImageFile;
import com.prodinv.repositories.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageFileService
{
    private ImageFileRepository repository;

    @Autowired
    public ImageFileService(ImageFileRepository repository)
    {
        this.repository = repository;
    }


}
