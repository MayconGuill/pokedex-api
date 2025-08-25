package com.pokedex.api.model.DTO;

import java.util.List;

public record PokemonResponseDTO(
        Long id,
        String name,
        Integer height,
        Integer weight,
        List<Stats> stats,
        List<TypeSlot> types) {
    public record Stats(int base_stat, Stat stat) {
        public record Stat(String name, String url) {
        }
    }
    public record TypeSlot(int slot, Type type) {
        public record Type(String name, String url) {
        }
    }
}
