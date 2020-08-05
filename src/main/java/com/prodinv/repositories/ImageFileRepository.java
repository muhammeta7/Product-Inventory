package com.prodinv.repositories;

import com.prodinv.models.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageFileRepository extends JpaRepository<ImageFile, Long>
{
    public Optional<ImageFile> findByFileName(String fileName);
}
