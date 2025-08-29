package com.pokedex.api.model.DTO;

import java.util.List;

public record PokemonSummaryDTO(
        Integer id,
        String name,
        String image,
        List<PokemonDetailsDTO.TypeDTO.Type> types
) {
}