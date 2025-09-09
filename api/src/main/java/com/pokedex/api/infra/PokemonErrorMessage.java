package com.pokedex.api.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class PokemonErrorMessage {
    private HttpStatus status;
    private String message;
}
