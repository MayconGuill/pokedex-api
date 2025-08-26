package com.pokedex.api.model.pokemon;

import com.pokedex.api.model.stat.PokemonStat;
import com.pokedex.api.model.type.PokemonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pokemon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer pokemonId;
    private String name;
    private Integer height;
    private Integer weight;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL)
    private List<PokemonStat> stats = new ArrayList<>();

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL)
    private List<PokemonType> types = new ArrayList<>();
}
