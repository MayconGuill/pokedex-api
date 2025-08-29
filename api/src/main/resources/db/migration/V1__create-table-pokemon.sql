CREATE TABLE pokemon(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    pokemon_id INTEGER UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    height INTEGER,
    weight INTEGER,
    image TEXT
);

CREATE TABLE stat(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE pokemon_stats(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    base_stat INTEGER NOT NULL,
    stat_id INTEGER NOT NULL,
    pokemon_id INTEGER NOT NULL,
    CONSTRAINT fk_stat FOREIGN KEY (stat_id) REFERENCES stat(id),
    CONSTRAINT fk_pokemon FOREIGN KEY (pokemon_id) REFERENCES pokemon(id),
    UNIQUE (stat_id, pokemon_id)
);

CREATE TABLE type(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE pokemon_type(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    slot INTEGER NOT NULL,
    type_id INTEGER NOT NULL,
    pokemon_id INTEGER NOT NULL,
    CONSTRAINT fk_type FOREIGN KEY (type_id) REFERENCES type(id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id),
    UNIQUE (type_id, pokemon_id)
);