package com.prodinv.services;

import com.prodinv.models.ImageFile;
import com.prodinv.models.Product;
import com.prodinv.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProductService
{
    private ProductRepository repository;
    private ImageFileService imageFileService;

    @Autowired
    public ProductService(ProductRepository repository, ImageFileService imageFileService)
    {
        this.repository = repository;
        this.imageFileService = imageFileService;
    }

    public Product create(Product newProduct)
    {
        return repository.save(newProduct);
    }

    // TODO Product Image Management -- Needs to be more elegant
    public Product create(Product newProduct, MultipartFile imageFile) throws IOException
    {
//        try
//        {
//            ImageFile img = imageFileService.uploadImage(imageFile);
////            TODO: When products can have multiple images
////            Set<ImageFile> pictures = new HashSet<>();
////            pictures.add(img);
////
////            newProduct.setPhoto(pictures);
//            newProduct.setPhoto(img);
//        }
//        catch(Exception e)
//        {
//            // Will log error
//        }

        newProduct.setPhoto(new ImageFile(imageFile.getOriginalFilename(), imageFile.getContentType(),
                imageFile.getBytes()));
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
