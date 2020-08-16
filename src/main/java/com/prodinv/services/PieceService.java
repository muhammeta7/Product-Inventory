package com.prodinv.services;

import com.prodinv.models.Piece;
import com.prodinv.repositories.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
