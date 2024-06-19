CREATE DATABASE IF NOT EXISTS historico;

USE historico;

CREATE TABLE aluno (
    cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    dataNascimento VARCHAR(10) NOT NULL,
    peso DOUBLE NOT NULL,
    altura INT NOT NULL
);

CREATE TABLE historico_peso (
    cpf_aluno VARCHAR(11),
    data VARCHAR(10) NOT NULL,
    peso DOUBLE NOT NULL,
    PRIMARY KEY (cpf_aluno, data),
    FOREIGN KEY (cpf_aluno) REFERENCES aluno(cpf) ON DELETE CASCADE
);

