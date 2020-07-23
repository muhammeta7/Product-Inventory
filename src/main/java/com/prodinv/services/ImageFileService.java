package com.prodinv.services;

import com.prodinv.models.ImageFile;
import com.prodinv.repositories.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ImageFileService
{
    private final static Logger logger = Logger.getLogger(ImageFileService.class.getName());
    private ImageFileRepository repository;

    @Autowired
    public ImageFileService(ImageFileRepository repository)
    {
        this.repository = repository;
    }

    public ImageFile uploadImage(MultipartFile file) throws IOException
    {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try
        {
            logger.log(Level.INFO, "File name: " + fileName);
            logger.log(Level.INFO, "Content Type: " + file.getContentType());
            byte[] fileBytes = file.getBytes();
            ImageFile img = new ImageFile(fileName, file.getContentType(), fileBytes);
            return repository.save(img);
        }
        catch(IOException e)
        {
            logger.log(Level.WARNING, "Error uploading file: " + fileName);
            return null;
        }
    }

    public ImageFile getImage(Long id) throws FileNotFoundException
    {
        return repository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + id));
    }
}
