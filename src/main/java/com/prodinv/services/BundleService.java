package com.prodinv.services;

import com.prodinv.exceptions.ResourceNotFoundException;
import com.prodinv.models.Bundle;
import com.prodinv.models.Piece;
import com.prodinv.models.Product;
import com.prodinv.repositories.BundleRepository;
import com.prodinv.repositories.PieceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BundleService
{
    Logger logger = LoggerFactory.getLogger(BundleService.class);
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
        logger.info(String.format("Bundle id %d", bundleForPiece.getId()));
        Product produceForPiece = productService.findById(productId)
                .orElseThrow(ResourceNotFoundException::new);
        logger.info(String.format("Product id %d", produceForPiece.getId()));
        Piece addedPiece = new Piece();
        addedPiece.setBundle(bundleForPiece);
        addedPiece.setProduct(produceForPiece);
        addedPiece.setDefaultQty(quantity);
        pieceService.create(addedPiece);
        return bundleRepository.save(bundleForPiece);
    }
}
