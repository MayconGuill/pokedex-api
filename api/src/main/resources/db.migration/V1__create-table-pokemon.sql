CREATE TABLE pokemon(
    id INTEGER PRIMARY KEY,
    id_poke_api INTEGER UNIQUE,
    name VARCHAR(100) NOT NULL,
    height INTEGER,
    weight INTEGER
);

CREATE TABLE stat(
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE pokemon_stats(
    id INTEGER PRIMARY KEY,
    base_stat INTEGER NOT NULL,
    stat_id INTEGER NOT NULL,
    pokemon_id INTEGER NOT NULL,
    CONSTRAINT fk_stat FOREIGN KEY (stat_id) REFERENCES stat(id),
    CONSTRAINT fk_pokemon FOREIGN KEY (pokemon_id) REFERENCES pokemon(id),
    UNIQUE (stat_id, pokemon_id)
);