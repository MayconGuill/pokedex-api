package com.pokedex.api.controller;

import com.pokedex.api.model.DTO.PokeApiResponseDTO;
import com.pokedex.api.model.DTO.PokemonResponseDTO;
import com.pokedex.api.model.pokemon.Pokemon;
import com.pokedex.api.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
@AllArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/{name}")
    public PokeApiResponseDTO getByName(@PathVariable String name) {
        return this.pokemonService.getPokemonByName(name.toLowerCase());
    }

    @GetMapping("/import/{name}")
    public ResponseEntity<PokemonResponseDTO> importPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.getPokemonByNameOnDemand(name.toLowerCase()));
    }


}
