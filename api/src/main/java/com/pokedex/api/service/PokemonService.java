package com.pokedex.api.service;

import com.pokedex.api.model.DTO.PokemonResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class PokemonService {

    private final WebClient webClient;

    public PokemonResponseDTO getPokemonByName(String name) {
        return webClient.get()
                .uri("/pokemon/{name}", name)
                .retrieve()
                .bodyToMono(PokemonResponseDTO.class)
                .block();
    }
}
