package com.prodinv.services;

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
    private final static Logger logger = Logger.getLogger(Product.class.getName());
    private ProductRepository repository;
    private ImageFileService imageFileService;

    @Autowired
    public ProductService(ProductRepository repository, ImageFileService imageFileService)
    {
        this.repository = repository;
        this.imageFileService = imageFileService;
    }

    // Attempt
    public Product createProd(Product newProduct, MultipartFile file) throws IOException
    {
        try{
            ImageFile uploadImg = imageFileService.uploadImage(file);
            newProduct.getPhotos().add(uploadImg);
        } catch(Exception e){
            logger.log(Level.INFO, "Error creating product");
            throw new IOException("Product could not be created!");
        }
        return  repository.save(newProduct);
    }

    public Product create(Product newProduct)
    {
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

    public Product findByName(String productName)
    {
        return repository.findByName(productName);
    }

    public Product findByAbbr(String abbr)
    {
        return repository.findByAbbr(abbr);
    }

    public Collection<Product> findByCategory(String category)
    {
        return repository.findByCategory(category);
    }

    // TODO Product Image Management

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
