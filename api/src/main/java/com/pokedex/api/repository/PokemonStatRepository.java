package com.pokedex.api.repository;

import com.pokedex.api.model.stat.PokemonStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonStatRepository extends JpaRepository<PokemonStat, Integer> {
}
