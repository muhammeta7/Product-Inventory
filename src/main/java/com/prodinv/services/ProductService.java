package com.prodinv.services;

import com.prodinv.exceptions.InvalidImageFileException;
import com.prodinv.models.ImageFile;
import com.prodinv.models.Product;
import com.prodinv.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;

@Service
public class ProductService
{
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Product create(Product newProduct)
    {
        return productRepository.save(newProduct);
    }

    // TODO Product Image Management -- Needs to be more elegant
    public Product create(Product newProduct, MultipartFile imageFile) throws IOException
    {
        // TODO: Need a better test for if imageFile is real or not
        try
        {
            ImageFile img = new ImageFile(imageFile.getOriginalFilename(), imageFile.getContentType(),
                    imageFile.getBytes());
            newProduct.getPhotos().add(img);
            img.setProduct(newProduct);
        }
        catch(Exception e)
        {
            if(e instanceof InvalidImageFileException)
            {
                throw new InvalidImageFileException();
            }
        }

        return productRepository.save(newProduct);
    }

    public Iterable<Product> index()
    {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id)
    {
        return productRepository.findById(id);
    }

    public Optional<Product> findByName(String productName)
    {
        return productRepository.findByName(productName);
    }

    public Optional<Product> findByAbbr(String abbr)
    {
        return productRepository.findByAbbr(abbr);
    }

    public Collection<Product> findByCategory(String category)
    {
        return productRepository.findByCategory(category);
    }

    public Collection<String> listCategories()
    {
        return productRepository.findCategories();
    }

    public Collection<String> listAbbreviations()
    {
        return productRepository.findAbbreviations();
    }

    public Collection<String> listLocations()
    {
        return productRepository.findLocations();
    }

    public Boolean delete(Long id)
    {
        productRepository.deleteById(id);
        return true;
    }
}
