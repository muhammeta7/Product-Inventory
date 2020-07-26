package com.prodinv.services;

import com.prodinv.models.Product;
import com.prodinv.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Set<String>  getCategories(){
        Set<String> categories = new TreeSet<>();
        List<Product> inventory = this.repository.findAll();
        for(Product p : inventory){
            categories.add(p.getCategory());
        }
        return categories;
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

    public Boolean delete(Long id)
    {
        repository.deleteById(id);
        return true;
    }
}
