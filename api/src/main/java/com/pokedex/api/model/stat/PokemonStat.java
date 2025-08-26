package com.pokedex.api.model.stat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pokedex.api.model.pokemon.Pokemon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemon_stats")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PokemonStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer baseStat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stat_id")
    private Stat stat;
}
