package com.pokedex.api.controller;

import com.pokedex.api.model.DTO.PokeApiResponseDTO;
import com.pokedex.api.model.DTO.PokemonSummaryDTO;
import com.pokedex.api.model.DTO.PokemonDetailsDTO;
import com.pokedex.api.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/page")
    public ResponseEntity<Page<PokemonSummaryDTO>> getPageableToPokemon(@RequestParam int page,
                                                                        @RequestParam int size) {
        return ResponseEntity.ok(pokemonService.getPageableToPokemon(page, size));
    }

    @GetMapping("/{name}")
    public ResponseEntity<PokemonSummaryDTO> getPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.getPokemonByName(name.toLowerCase()));
    }
}
