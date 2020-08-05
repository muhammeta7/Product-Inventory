package com.prodinv.repositories;

import com.prodinv.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{

    Optional<Photo> findByName(String name);
}
