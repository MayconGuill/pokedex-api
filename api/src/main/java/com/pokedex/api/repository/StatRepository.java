package com.pokedex.api.repository;

import com.pokedex.api.model.stat.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatRepository extends JpaRepository<Stat, Integer> {
    Optional<Stat> findByName(String name);
}
