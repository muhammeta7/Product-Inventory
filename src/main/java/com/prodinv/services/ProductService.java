package com.prodinv.services;

import com.prodinv.exceptions.InvalidImageFileException;
import com.prodinv.models.ImageFile;
import com.prodinv.models.Product;
import com.prodinv.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductService
{
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository, ImageFileService imageFileService)
    {
        this.repository = repository;
    }

    public Product create(Product newProduct)
    {
        return repository.save(newProduct);
    }

    // TODO Product Image Management -- Needs to be more elegant
    public Product create(Product newProduct, MultipartFile imageFile) throws IOException
    {
        // TODO: TNeed a better test for if imageFile is real or not
        if(imageFile.getBytes().length > 0)
        {
            try
            {
                ImageFile img = new ImageFile(imageFile.getOriginalFilename(), imageFile.getContentType(),
                        imageFile.getBytes());
                Set<ImageFile> imgSet = new HashSet<>();
                imgSet.add(img);
                newProduct.setPhotos(imgSet);
            }
            catch(Exception e)
            {
                throw new InvalidImageFileException();
            }
        }

        return repository.save(newProduct);
    }

    public Iterable<Product> index()
    {
        return repository.findAll();
    }

    public Optional<Product> findById(Long id)
    {
        return repository.findById(id);
    }

    public Optional<Product> findByName(String productName)
    {
        return repository.findByName(productName);
    }

    public Optional<Product> findByAbbr(String abbr)
    {
        return repository.findByAbbr(abbr);
    }

    public Collection<Product> findByCategory(String category)
    {
        return repository.findByCategory(category);
    }

    public Collection<String> listCategories()
    {
        return repository.findCategories();
    }

    public Collection<String> listAbbreviations()
    {
        return repository.findAbbreviations();
    }

    public Collection<String> listLocations()
    {
        return repository.findLocations();
    }

    public Boolean delete(Long id)
    {
        repository.deleteById(id);
        return true;
    }
}
