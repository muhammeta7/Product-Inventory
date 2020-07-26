package com.prodinv.repositories;

import com.prodinv.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
//    @Query("SELECT p FROM Product p WHERE p.name = :name")
//    Product findByName(@Param("name") String searchTerm);

    Product findByName(String searchTerm);

    @Query("SELECT p FROM Product p WHERE p.abbreviation = :abbreviation")
    Product findByAbbr(@Param("abbreviation") String searchTerm);

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    Collection<Product> findByCategory(@Param("category") String searchTerm);

    @Query("SELECT DISTINCT p.category FROM Product p ORDER BY p.category ASC")
    Collection<String> findCategories();

    @Query("SELECT DISTINCT p.abbreviation FROM Product p ORDER BY p.abbreviation ASC")
    Collection<String> findAbbreviations();
}
