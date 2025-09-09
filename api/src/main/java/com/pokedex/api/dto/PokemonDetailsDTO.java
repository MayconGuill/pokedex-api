package com.pokedex.api.dto;

import java.util.List;

public record PokemonDetailsDTO(
        Integer id,
        String name,
        Integer height,
        Integer weight,
        String image,
        List<StatDTO> stats,
        List<TypeDTO> types
) {
    public record StatDTO(Integer base_stat, Stat stat) {
        public record Stat(String name) {
        }
    }
    public record TypeDTO(int slot, Type type) {
        public record Type(String name) {
        }
    }
}
