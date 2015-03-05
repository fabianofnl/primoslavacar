DROP TABLE IF EXISTS fluxocaixa;
DROP TABLE IF EXISTS conta;
DROP TABLE IF EXISTS agenda;
DROP TABLE IF EXISTS servico;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS funcionario;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS perfil;

CREATE TABLE perfil (
	id SERIAL NOT NULL PRIMARY KEY,
	descricao VARCHAR(100) NOT NULL,
	roleName VARCHAR(100) NOT NULL
);

CREATE TABLE usuario (
	usuario VARCHAR(100) NOT NULL PRIMARY KEY,
	senha VARCHAR(100) NOT NULL,
	perfilId INTEGER NOT NULL REFERENCES perfil(id)
);

CREATE TABLE funcionario (
	cpf NUMERIC(11) NOT NULL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	status VARCHAR(10) DEFAULT 'Ativo',
	usuario VARCHAR(100) NOT NULL REFERENCES usuario(usuario)
);

CREATE TABLE cliente (
	cpf NUMERIC(11) NOT NULL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	endereconumero VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	telefone NUMERIC(10) NOT NULL,
	status VARCHAR(10) NOT NULL
);

CREATE TABLE servico (
	id SERIAL NOT NULL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	descricao TEXT NOT NULL,
	valor NUMERIC(18,2) NOT NULL,
	status VARCHAR(10) NOT NULL
);

CREATE TABLE agenda (
	id SERIAL NOT NULL PRIMARY KEY,
	idServico INTEGER NOT NULL REFERENCES servico(id),
	cpf NUMERIC(11) NOT NULL REFERENCES cliente(cpf),
	dataInicio TIMESTAMP NOT NULL,
	dataFim TIMESTAMP NOT NULL,
	baixa BOOLEAN NOT NULL
);

CREATE TABLE conta (
	id SERIAL NOT NULL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	descricao TEXT NOT NULL,
	status VARCHAR(10) NOT NULL
);

CREATE TABLE fluxocaixa (
	id SERIAL NOT NULL PRIMARY KEY,
	titulo VARCHAR(100) NOT NULL,
	tipo VARCHAR(1) NOT NULL,
	dataProcessamento DATE NOT NULL,
	valor NUMERIC(18,2) NOT NULL,
	idAgenda INTEGER
);

INSERT INTO perfil (descricao, roleName) VALUES ('Administrador', 'ROLE_ADMIN'); -- 1
INSERT INTO perfil (descricao, roleName) VALUES ('Usu�rio', 'ROLE_USER'); -- 2

INSERT INTO usuario (usuario, senha, perfilId) VALUES ('jsilva', MD5('123'), 1);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('apaula', MD5('123'), 2);

INSERT INTO funcionarios (cpf, nome, email, usuario) VALUES (12345678911,'Jo�o Silva','jsilva@teste.com','jsilva');
INSERT INTO funcionarios (cpf, nome, email, usuario) VALUES (12345678922,'Ana Paula','apaula@teste.com','apaula');