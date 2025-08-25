package com.pokedex.api.controller;

import com.pokedex.api.model.DTO.PokemonResponseDTO;
import com.pokedex.api.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemon")
@AllArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/{name}")
    public PokemonResponseDTO getByName(@PathVariable String name) {
        return this.pokemonService.getPokemonByName(name.toLowerCase());
    }
}
