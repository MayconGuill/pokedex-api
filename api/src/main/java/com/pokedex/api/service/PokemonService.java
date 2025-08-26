package com.pokedex.api.service;

import com.pokedex.api.model.DTO.PokeApiResponseDTO;
import com.pokedex.api.model.DTO.PokemonResponseDTO;
import com.pokedex.api.model.pokemon.Pokemon;
import com.pokedex.api.model.stat.PokemonStat;
import com.pokedex.api.model.stat.Stat;
import com.pokedex.api.model.type.PokemonType;
import com.pokedex.api.model.type.Type;
import com.pokedex.api.repository.PokemonRepository;
import com.pokedex.api.repository.PokemonStatRepository;
import com.pokedex.api.repository.StatRepository;
import com.pokedex.api.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PokemonService {

    private final WebClient webClient;
    private final PokemonRepository repository;
    private final StatRepository statRepository;
    private final TypeRepository typeRepository;

    public PokeApiResponseDTO getPokemonByName(String name) {
        return webClient.get()
                .uri("/pokemon/{name}", name)
                .retrieve()
                .bodyToMono(PokeApiResponseDTO.class)
                .block();
    }

    private PokemonResponseDTO toResponseDTO(Pokemon pokemon) {
        List<PokemonResponseDTO.StatDTO> stats = pokemon.getStats().stream()
                .map(pokemonStat -> new PokemonResponseDTO.StatDTO(
                        pokemonStat.getBaseStat(),
                        new PokemonResponseDTO.StatDTO.Stat(pokemonStat.getStat().getName()
                ))).toList();

        List<PokemonResponseDTO.TypeDTO> types = pokemon.getTypes().stream()
                .map(pokemonType -> new PokemonResponseDTO.TypeDTO(
                        pokemonType.getSlot(),
                        new PokemonResponseDTO.TypeDTO.Type(pokemonType.getType().getName())
                ))
                .toList();

        return new PokemonResponseDTO(
                pokemon.getPokemonId(),
                pokemon.getName(),
                pokemon.getHeight(),
                pokemon.getWeight(),
                stats,
                types
        );
    }


    public PokemonResponseDTO getPokemonByNameOnDemand(String name) {
        Optional<Pokemon> pokemon = repository.findByName(name);

        if (pokemon.isPresent()) {
            return toResponseDTO(pokemon.get());
        }

        PokeApiResponseDTO data = webClient.get()
                .uri("/pokemon/{name}", name)
                .retrieve()
                .bodyToMono(PokeApiResponseDTO.class)
                .block();

        if (data == null) {
            throw new RuntimeException("Pokemon não encontrado na API");
        }

        Pokemon poke = new Pokemon();
        poke.setPokemonId(data.id());
        poke.setName(data.name());
        poke.setHeight(data.height());
        poke.setWeight(data.weight());
        poke.setStats(new ArrayList<>());
        poke.setTypes(new ArrayList<>());

        data.stats().forEach(stats -> {
            Stat stat = statRepository.findByName(stats.stat().name())
                    .orElseGet(() -> {
                        Stat newStat = new Stat();
                        newStat.setName(stats.stat().name());
                        return statRepository.save(newStat);
                    });

            PokemonStat pokemonStat = new PokemonStat();
            pokemonStat.setBaseStat(stats.base_stat());
            pokemonStat.setPokemon(poke);
            pokemonStat.setStat(stat);

            poke.getStats().add(pokemonStat);
        });

        data.types().forEach(typeSlot -> {
            Type type = typeRepository.findByName(typeSlot.type().name())
                    .orElseGet(() -> {
                        Type newType = new Type();
                        newType.setName(typeSlot.type().name());
                        return typeRepository.save(newType);
                    });

            PokemonType pokemonType = new PokemonType();
            pokemonType.setSlot(typeSlot.slot());
            pokemonType.setPokemon(poke);
            pokemonType.setType(type);

            poke.getTypes().add(pokemonType);
        });
        Pokemon saved = repository.save(poke);

        return toResponseDTO(saved);
    }
}
