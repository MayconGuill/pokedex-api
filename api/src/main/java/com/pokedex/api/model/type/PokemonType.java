package com.pokedex.api.model.type;

import com.pokedex.api.model.pokemon.Pokemon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemon_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PokemonType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer slot;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private Type type;
}
