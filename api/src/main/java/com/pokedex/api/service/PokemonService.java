package com.pokedex.api.service;

import com.pokedex.api.model.DTO.PokeApiResponseDTO;
import com.pokedex.api.model.DTO.PokemonSummaryDTO;
import com.pokedex.api.model.DTO.PokemonDetailsDTO;
import com.pokedex.api.model.pokemon.Pokemon;
import com.pokedex.api.model.stat.PokemonStat;
import com.pokedex.api.model.stat.Stat;
import com.pokedex.api.model.type.PokemonType;
import com.pokedex.api.model.type.Type;
import com.pokedex.api.repository.PokemonRepository;
import com.pokedex.api.repository.StatRepository;
import com.pokedex.api.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class PokemonService {

    private final WebClient webClient;
    private final PokemonRepository repository;
    private final StatRepository statRepository;
    private final TypeRepository typeRepository;

    public PokeApiResponseDTO getPokemonByNameForApi(String name) {
        return webClient.get()
                .uri("/pokemon/{name}", name)
                .retrieve()
                .bodyToMono(PokeApiResponseDTO.class)
                .block();
    }

    public Page<PokemonSummaryDTO> getPageableToPokemon(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pokemon> pagePokemon = repository.findAll(pageable);

        return pagePokemon.map(pokemons -> {
                    List<PokemonDetailsDTO.TypeDTO.Type> types = pokemons.getTypes().stream()
                            .map(pokemonType -> new PokemonDetailsDTO.TypeDTO.Type(
                                    pokemonType.getType().getName()
                            )).toList();
                    return new PokemonSummaryDTO(
                            pokemons.getPokemonId(),
                            pokemons.getName(),
                            pokemons.getImage(),
                            types
                    );
                });
    }

    public PokemonSummaryDTO getPokemonByName(String name) {
        Pokemon pokemon = repository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Pokemon nao localizado"));

        List<PokemonDetailsDTO.TypeDTO.Type> types = pokemon.getTypes().stream()
                .map(pokemonType -> new PokemonDetailsDTO.TypeDTO.Type(
                        pokemonType.getType().getName()
                )).toList();

        return new PokemonSummaryDTO(
                pokemon.getPokemonId(),
                pokemon.getName(),
                pokemon.getImage(),
                types
        );
    }

    public Page<PokemonSummaryDTO> getPokemonOrdainedByGreatest(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("pokemonId").descending());
        Page<Pokemon> pagePokemon = repository.findAll(pageable);

        return pagePokemon.map(pokemon -> {
            List<PokemonDetailsDTO.TypeDTO.Type> types = pokemon.getTypes().stream()
                    .map(pokemonType -> new PokemonDetailsDTO.TypeDTO.Type(
                            pokemonType.getType().getName()
                    )).toList();
            return new PokemonSummaryDTO(
                    pokemon.getPokemonId(),
                    pokemon.getName(),
                    pokemon.getImage(),
                    types
            );
        });
    }

    public Page<PokemonSummaryDTO> getPokemonOrdainedBySmallest(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("pokemonId").ascending());
        Page<Pokemon> pagePokemon = repository.findAll(pageable);

        return pagePokemon.map(pokemon -> {
            List<PokemonDetailsDTO.TypeDTO.Type> types = pokemon.getTypes().stream()
                    .map(pokemonType -> new PokemonDetailsDTO.TypeDTO.Type(
                            pokemonType.getType().getName()
                    )).toList();
            return new PokemonSummaryDTO(
                    pokemon.getPokemonId(),
                    pokemon.getName(),
                    pokemon.getImage(),
                    types
            );
        });
    }

    public PokemonDetailsDTO postPokemonByName(String name) {
        PokeApiResponseDTO data = webClient.get()
                .uri("/pokemon/{name}", name)
                .retrieve()
                .bodyToMono(PokeApiResponseDTO.class)
                .block();

        if (data == null) {
            throw new RuntimeException("Pokemon nao encontrado na API");
        }

        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonId(data.id());
        pokemon.setName(data.name());
        pokemon.setHeight(data.height());
        pokemon.setWeight(data.weight());
        pokemon.setImage(data.sprites().other().dream_world().front_default());

        List<PokemonStat> stats = data.stats().stream()
                .map(statsDto -> {
                    Stat stat = statRepository.findByName(statsDto.stat().name())
                            .orElseGet(() -> {
                                Stat newStat = new Stat();
                                newStat.setName(statsDto.stat().name());
                                return statRepository.save(newStat);
                            });

                    PokemonStat pokemonStat = new PokemonStat();
                    pokemonStat.setBaseStat(statsDto.base_stat());
                    pokemonStat.setPokemon(pokemon);
                    pokemonStat.setStat(stat);

                    return pokemonStat;
                })
                .toList();

       pokemon.setStats(stats);

        List<PokemonType> types = data.types().stream()
                .map(typesDto -> {
                    Type type = typeRepository.findByName(typesDto.type().name())
                            .orElseGet(() -> {
                                Type newType = new Type();
                                newType.setName(typesDto.type().name());
                                return typeRepository.save(newType);
                            });

                    PokemonType pokemonType = new PokemonType();
                    pokemonType.setSlot(typesDto.slot());
                    pokemonType.setPokemon(pokemon);
                    pokemonType.setType(type);

                    return pokemonType;
                })
                .toList();

       pokemon.setTypes(types);

       Pokemon pokemonSaved = repository.save(pokemon);

       return new PokemonDetailsDTO(
               pokemonSaved.getPokemonId(),
               pokemonSaved.getName(),
               pokemonSaved.getHeight(),
               pokemonSaved.getWeight(),
               pokemonSaved.getImage(),
               pokemonSaved.getStats().stream()
                       .map(pokemonStat -> new PokemonDetailsDTO.StatDTO(
                               pokemonStat.getBaseStat(),
                               new PokemonDetailsDTO.StatDTO.Stat(pokemonStat.getStat().getName())
                       )).toList(),
               pokemonSaved.getTypes().stream()
                       .map(pokemonType -> new PokemonDetailsDTO.TypeDTO(
                               pokemonType.getSlot(),
                               new PokemonDetailsDTO.TypeDTO.Type(pokemonType.getType().getName())
                       )).toList()
       );
    }
}
