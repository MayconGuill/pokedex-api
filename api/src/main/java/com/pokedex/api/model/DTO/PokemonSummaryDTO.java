package com.pokedex.api.model.DTO;

import java.util.List;

public record PokemonSummaryDTO(
        Integer id,
        String name,
        List<PokemonDetailsDTO.TypeDTO.Type> types
) {
}
