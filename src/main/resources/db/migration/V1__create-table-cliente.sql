CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    sobrenome VARCHAR(255) NOT NULL,
    rg VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    dataNascimento DATE NOT NULL,
    dataCadastro TIMESTAMP
);

