package com.prodinv.repositories;

import com.prodinv.models.Bundle;
import com.prodinv.models.Piece;
import com.prodinv.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Long>
{
    public Optional<Piece> findByBundleAndProduct(Bundle bundle, Product product);
}
