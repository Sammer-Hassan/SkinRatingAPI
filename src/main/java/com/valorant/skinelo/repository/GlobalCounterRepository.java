package com.valorant.skinelo.repository;

import com.valorant.skinelo.entity.GlobalCounter;
import org.springframework.data.repository.CrudRepository;

public interface GlobalCounterRepository extends CrudRepository<GlobalCounter, Long> {
}
