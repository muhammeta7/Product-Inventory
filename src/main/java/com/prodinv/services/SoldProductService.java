package com.prodinv.services;

import com.prodinv.models.SoldProduct;
import com.prodinv.repositories.SoldProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class SoldProductService
{
    private final SoldProductRepository repository;

    @Autowired
    public SoldProductService(SoldProductRepository repository)
    {
        this.repository = repository;
    }

    public SoldProduct create(SoldProduct newSoldProduct)
    {
        return repository.save(newSoldProduct);
    }

    public Optional<SoldProduct> findById(Long id)
    {
        return repository.findById(id);
    }

    public Optional<SoldProduct> findByPurchaser(String purchaser)
    {
        return repository.findSoldProductsByPurchaser(purchaser);
    }

    public SoldProduct shipProduct(Long id)
    {
        LocalDateTime today = LocalDateTime.now();
        SoldProduct preShip = repository.findById(id).get();

        return repository.findById(id)
                .map(soldProduct ->
                {
                    soldProduct.setSoldProduct(preShip.getSoldProduct());
                    soldProduct.setPurchaser(preShip.getPurchaser());
                    soldProduct.setSellDate(preShip.getSellDate());
                    soldProduct.setShipDate(today);
                    soldProduct.setQty(preShip.getQty());
                    return repository.save(soldProduct);
                })
                .orElseGet(() -> repository.save(preShip));
    }
}
