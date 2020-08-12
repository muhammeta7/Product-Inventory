package com.prodinv.repositories;

import com.prodinv.compositekeys.ComponentKey;
import com.prodinv.models.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, ComponentKey>
{
}
