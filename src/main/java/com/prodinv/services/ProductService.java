package com.prodinv.services;

import com.prodinv.exceptions.InvalidImageFileException;
import com.prodinv.exceptions.ProductNotFoundException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductService
{
    private final ProductRepository productRepository;
    private final Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Product create(Product newProduct)
    {
        return productRepository.save(newProduct);
    }

    public Product create(Product newProduct, MultipartFile imageFile) throws IOException
    {
        try
        {
            ImageFile img = new ImageFile(imageFile.getOriginalFilename(), imageFile.getContentType(),
                    imageFile.getBytes());
            logger.log(Level.INFO, "UPLOAD File name: " + img.getFileName());
            logger.log(Level.INFO, "UPLOAD Content Type: " + img.getType());
            logger.log(Level.INFO, String.format("UPLOAD File size: %.2f KB", img.getImgBytes().length / 1024.0));
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

    public Product update(Long id, Product updatedProduct)
    {
        return this.productRepository.findById(id)
                .map(product -> {
                    product.setName(updatedProduct.getName());
                    product.setAbbreviation(updatedProduct.getAbbreviation());
                    product.setLocation(updatedProduct.getLocation());
                    product.setLength(updatedProduct.getLength());
                    product.setWidth(updatedProduct.getWidth());
                    product.setDepth(updatedProduct.getDepth());
                    product.setQty(updatedProduct.getQty());
                    product.setDescription(updatedProduct.getDescription());
                    product.setCategory(updatedProduct.getCategory());
                    product.setPhotos(updatedProduct.getPhotos());

                    return productRepository.save(product);
                })
                .orElseGet(() -> productRepository.save(updatedProduct));
    }




    public Product attachPhoto(Long productId, MultipartFile file) throws IOException
    {
        ImageFile img = new ImageFile(file.getOriginalFilename(), file.getContentType(), file.getBytes());

        return this.productRepository.findById(productId)
                .map(product ->
                {
                    product.setName(product.getName());
                    product.setAbbreviation(product.getAbbreviation());
                    product.setLocation(product.getLocation());
                    product.setLength(product.getLength());
                    product.setWidth(product.getWidth());
                    product.setDepth(product.getDepth());
                    product.setQty(product.getQty());
                    product.setDescription(product.getDescription());
                    product.setCategory(product.getCategory());
                    product.setPhotos(product.getPhotos());

                    product.getPhotos().add(img);
                    img.setProduct(product);

                    return productRepository.save(product);
                })
                .orElseThrow(ProductNotFoundException::new);
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

    public Product increaseQuantity(Long id, Integer quantity)
    {
        Product original = productRepository.getOne(id);
        original.setQty(original.getQty() + quantity);
        return productRepository.save(original);
    }

    public Product decreaseQuantity(Long id, Integer quantity)
    {
        Product original = productRepository.getOne(id);
        if(original.getQty() - quantity < 0)
        {
            throw new IllegalArgumentException("Too many");
        }
        else
            {
            original.setQty(original.getQty() - quantity);
        }
        return productRepository.save(original);
    }

    public Boolean delete(Long id)
    {
        if(productRepository.findById(id).isPresent())
        {
            this.productRepository.deleteById(id);

            return true;
        }
        else
        {
            return false;
        }
    }
}
