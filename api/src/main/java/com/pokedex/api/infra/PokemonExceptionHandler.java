package com.pokedex.api.infra;

import com.pokedex.api.exception.PokemonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PokemonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<PokemonErrorMessage> pokemonNotFoundHandler(PokemonNotFoundException exception) {
        PokemonErrorMessage pokemonErrorMessage = new PokemonErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pokemonErrorMessage);
    }
}
