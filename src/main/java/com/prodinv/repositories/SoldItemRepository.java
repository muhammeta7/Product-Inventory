package com.prodinv.repositories;

import com.prodinv.models.SoldItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldItemRepository extends JpaRepository<SoldItem, Long>
{
}
