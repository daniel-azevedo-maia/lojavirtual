CREATE TABLE estado (
    id SERIAL,
    nome VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE cidade (
    id SERIAL,
    nome VARCHAR(50) NOT NULL,
    estado_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (estado_id) REFERENCES estado(id)
);