package com.pokedex.api.controller;

import com.pokedex.api.dto.PokeApiResponseDTO;
import com.pokedex.api.dto.PokemonSummaryDTO;
import com.pokedex.api.dto.PokemonDetailsDTO;
import com.pokedex.api.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/pokemon/external/{name}")
    public PokeApiResponseDTO getByNameForApi(@PathVariable String name) {
        return this.pokemonService.getPokemonByNameForApi(name.toLowerCase());
    }

    @PostMapping("/pokemon/{name}")
    public ResponseEntity<PokemonDetailsDTO> importPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.postPokemonByName(name.toLowerCase()));
    }

    @GetMapping("/pokemon/page")
    public ResponseEntity<Page<PokemonSummaryDTO>> getPageableToPokemon(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "12") int size) {
        return ResponseEntity.ok(pokemonService.getPageableToPokemon(page, size));
    }

    @GetMapping("/pokemon/{name}")
    public ResponseEntity<PokemonSummaryDTO> getPokemonByName(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.getPokemonByName(name.toLowerCase()));
    }

    @GetMapping("/pokemon/greatest")
    public ResponseEntity<Page<PokemonSummaryDTO>> getPokemonOrdainedByGreatest(@RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "12") int size) {
        return ResponseEntity.ok(pokemonService.getPokemonOrdainedByGreatest(page, size));
    }

    @GetMapping("/pokemon/smallest")
    public ResponseEntity<Page<PokemonSummaryDTO>> getPokemonOrdainedBySmallest(@RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "12") int size) {
        return ResponseEntity.ok(pokemonService.getPokemonOrdainedBySmallest(page, size));
    }
}
