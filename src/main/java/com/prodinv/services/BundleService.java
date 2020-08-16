package com.prodinv.services;

import com.prodinv.exceptions.ResourceNotFoundException;
import com.prodinv.models.Bundle;
import com.prodinv.models.Piece;
import com.prodinv.models.Product;
import com.prodinv.repositories.BundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
