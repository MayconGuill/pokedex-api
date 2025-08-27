CREATE TABLE type(
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
);

CREATE TABLE pokemon_type(
    id INTEGER PRIMARY KEY,
    slot INTEGER NOT NULL,
    type_id INTEGER NOT NULL,
    pokemon_id INTEGER NOT NULL,
    CONSTRAINT fk_type FOREIGN KEY (type_id) REFERENCES type(id),
    CONSTRAINT fk_pokemon FOREIGN KEY (pokemon_id) REFERENCES pokemon(id),
    UNIQUE (type_id, pokemon_id)
);