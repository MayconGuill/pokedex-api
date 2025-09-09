package com.pokedex.api.exception;

public class PokemonNotFoundException extends RuntimeException{
    public PokemonNotFoundException() {
        super("Pokémon não encontrado na PokeAPI");
    }

    public PokemonNotFoundException(String message) {
        super(message);
    }
}
