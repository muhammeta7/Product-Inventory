package com.prodinv.services;

import com.prodinv.exceptions.ResourceNotFoundException;
import com.prodinv.models.Bundle;
import com.prodinv.models.Piece;
import com.prodinv.models.Product;
import com.prodinv.repositories.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PieceService
{
    private PieceRepository pieceRepository;

    @Autowired
    public PieceService(PieceRepository pieceRepository)
    {
        this.pieceRepository = pieceRepository;
    }

    public Piece create(Piece createdPiece)
    {
        return pieceRepository.save(createdPiece);
    }

    public Optional<Piece> findByBundleAndProduct(Bundle bundle, Product product)
    {
            return pieceRepository.findByBundleAndProduct(bundle, product);
    }

    public Boolean delete(Long id)
    {
        if(pieceRepository.findById(id).isPresent())
        {
            pieceRepository.deleteById(id);

            return true;
        }
        else
        {
            return false;
        }
    }
}
