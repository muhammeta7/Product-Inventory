package com.prodinv.services;

import com.prodinv.models.Product;
import com.prodinv.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService
{
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository)
    {
        this.repository = repository;
    }

    public Product create(Product newProduct)
    {
        return repository.save(newProduct);
    }

    public Iterable<Product> index()
    {
        return repository.findAll();
    }

    public Product findById(Long id)
    {
        return repository.findById(id).get();
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

    public Boolean delete(Long id)
    {
        repository.deleteById(id);
        return true;
    }
}
