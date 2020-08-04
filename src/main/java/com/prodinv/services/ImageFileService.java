package com.prodinv.services;

import com.prodinv.models.ImageFile;
import com.prodinv.repositories.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ImageFileService
{
    private final static Logger logger = Logger.getLogger(ImageFileService.class.getName());
    private final ImageFileRepository repository;

    @Autowired
    public ImageFileService(ImageFileRepository repository)
    {
        this.repository = repository;
    }

    public ImageFile uploadImage(MultipartFile file) throws IOException
    {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(!file.getContentType().contains("image/"))
        {
            throw new IOException("Endpoint only accepts valid image files.  File was of type: " + file.getContentType() + "   ");
        }

        logger.log(Level.INFO, "UPLOAD File name: " + fileName);
        logger.log(Level.INFO, "UPLOAD Content Type: " + file.getContentType());
        byte[] fileBytes = file.getBytes();
        logger.log(Level.INFO, String.format("UPLOAD File size: %.2f KB", fileBytes.length / 1024.0));
        ImageFile img = new ImageFile(fileName, file.getContentType(), fileBytes);

        return repository.save(img);
    }

    public ImageFile findById(Long id) throws FileNotFoundException
    {
        return repository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + id));
    }

    @Transactional
    public Optional<ImageFile> findByName(String name) throws FileNotFoundException
    {
        return repository.findByFileName(name);
    }
}