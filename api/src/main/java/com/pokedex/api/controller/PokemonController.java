package com.pokedex.api.controller;

import com.pokedex.api.model.DTO.PokeApiResponseDTO;
import com.pokedex.api.model.DTO.PokemonSummaryDTO;
import com.pokedex.api.model.DTO.PokemonDetailsDTO;
import com.pokedex.api.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
@AllArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/external/{name}")
    public PokeApiResponseDTO getByNameForApi(@PathVariable String name) {
        return this.pokemonService.getPokemonByNameForApi(name.toLowerCase());
    }

    @PostMapping("/{name}")
    public ResponseEntity<PokemonDetailsDTO> importPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.postPokemonByName(name.toLowerCase()));
    }

    @GetMapping("/{name}")
    public ResponseEntity<PokemonSummaryDTO> getPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.getPokemonByName(name.toLowerCase()));
    }
}
