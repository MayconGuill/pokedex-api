package com.pokedex.api.repository;

import com.pokedex.api.model.type.PokemonType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonTypeRepository extends JpaRepository<PokemonType, Integer> {

}
