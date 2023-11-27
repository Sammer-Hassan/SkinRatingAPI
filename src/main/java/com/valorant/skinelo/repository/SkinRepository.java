package com.valorant.skinelo.repository;

import com.valorant.skinelo.entity.Skin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SkinRepository extends CrudRepository<Skin, Long> {

    @Query(value = "SELECT * FROM skins ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Skin findRandomEntity();
}
