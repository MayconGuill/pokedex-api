package com.pokedex.api.model.stat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "stat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "stat", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PokemonStat> pokemonStats;
}
