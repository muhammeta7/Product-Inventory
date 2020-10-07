package com.prodinv.services;

import com.prodinv.exceptions.ResourceNotFoundException;
import com.prodinv.models.Bundle;
import com.prodinv.models.Piece;
import com.prodinv.models.Product;
import com.prodinv.models.ProductCount;
import com.prodinv.repositories.BundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BundleService
{
    private final BundleRepository bundleRepository;
    private final ProductService productService;
    private final PieceService pieceService;

    @Autowired
    public BundleService(BundleRepository bundleRepository, ProductService productService, PieceService pieceService)
    {
        this.bundleRepository = bundleRepository;
        this.productService = productService;
        this.pieceService = pieceService;
    }

    public Bundle create(Bundle bundle)
    {
        return bundleRepository.save(bundle);
    }

    public Iterable<Bundle> findAll()
    {
        return bundleRepository.findAll();
    }

    public Optional<Bundle> findById(Long id)
    {
        return bundleRepository.findById(id);
    }

    public Bundle add(Long bundleId, Long productId, Integer quantity)
    {
        Bundle bundleForPiece = findById(bundleId)
                .orElseThrow(ResourceNotFoundException::new);
        Product produceForPiece = productService.findById(productId)
                .orElseThrow(ResourceNotFoundException::new);
        Piece addedPiece = new Piece();
        addedPiece.setBundle(bundleForPiece);
        addedPiece.setProduct(produceForPiece);
        addedPiece.setDefaultQty(quantity);
        pieceService.create(addedPiece);
        return bundleRepository.save(bundleForPiece);
    }


    public Bundle addMultiple(Long bundleId, List<ProductCount> list)
    {
        Bundle bundleForPiece = findById(bundleId)
                .orElseThrow(ResourceNotFoundException::new);
        List<Piece> temp = new ArrayList<>();
        for(ProductCount pc : list){
            Product produceForPiece = productService.findById(pc.getProductId())
                    .orElseThrow(ResourceNotFoundException::new);
            Piece addedPiece = new Piece();
            addedPiece.setBundle(bundleForPiece);
            addedPiece.setProduct(produceForPiece);
            addedPiece.setDefaultQty(pc.getQty());
            temp.add(addedPiece);
        }
        for(Piece p : temp){
            pieceService.create(p);
        }
        return bundleRepository.save(bundleForPiece);
    }

    public Bundle remove(Long bundleId, Long productId)
    {
        Bundle bundleForPiece = findById(bundleId)
                .orElseThrow(ResourceNotFoundException::new);
        Product produceForPiece = productService.findById(productId)
                .orElseThrow(ResourceNotFoundException::new);
        Long pieceId = pieceService.findByBundleAndProduct(bundleForPiece, produceForPiece).get().getId();

        pieceService.delete(pieceId);
        return bundleRepository.save(bundleForPiece);
    }

    public Boolean delete(Long id)
    {
        if(bundleRepository.findById(id).isPresent())
        {
            this.bundleRepository.deleteById(id);

            return true;
        }
        else
        {
            return false;
        }
    }
}
