package com.prodinv.repositories;

import com.prodinv.models.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSetRepository extends JpaRepository<Bundle, Long>
{
}
