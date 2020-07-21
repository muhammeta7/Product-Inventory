package com.prodinv.repositories;

import com.prodinv.models.SoldProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoldProductRepository extends JpaRepository<SoldProduct, Long>
{
    public Optional<SoldProduct> findSoldProductsByPurchaser(String purchaser);
}
