package com.pokedex.api.dto;

import java.util.List;

public record PokeApiResponseDTO(
        Integer id,
        String name,
        Integer height,
        Integer weight,
        Sprites sprites,
        List<Stats> stats,
        List<TypeSlot> types) {
    public record Sprites(Other other) {
        public record Other(DreamWorld dream_world) {
            public record DreamWorld(String front_default) {
            }
        }
    }
    public record Stats(int base_stat, Stat stat) {
        public record Stat(String name) {
        }
    }
    public record TypeSlot(int slot, Type type) {
        public record Type(String name) {
        }
    }
}
