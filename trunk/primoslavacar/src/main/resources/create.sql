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
INSERT INTO perfil (descricao, roleName) VALUES ('Usuário', 'ROLE_USER'); -- 2

INSERT INTO usuario (usuario, senha, perfilId) VALUES ('jsilva', MD5('123'), 1);
INSERT INTO usuario (usuario, senha, perfilId) VALUES ('apaula', MD5('123'), 2);

INSERT INTO funcionarios (cpf, nome, email, usuario) VALUES (12345678911,'João Silva','jsilva@teste.com','jsilva');
INSERT INTO funcionarios (cpf, nome, email, usuario) VALUES (12345678922,'Ana Paula','apaula@teste.com','apaula');

-- Ideia para extrair dados mensais
select to_char(dataProcessamento, 'mm') as mes, dataProcessamento, sum(valor) as valor from fluxocaixa 
WHERE tipo = 'R' AND extract (year from dataprocessamento) = 2015
group by 1, 2
order by 1

select f.titulo,
	sum((select sum(f1.valor)
		from fluxocaixa f1 
		where  extract (year from dataprocessamento) = 2015 
		and extract (month from dataprocessamento) = 4 and f1.id = f.id)) as janeiro 
from fluxocaixa f where f.tipo = 'R'
group by 1

CREATE OR REPLACE FUNCTION fluxocaixa (tipo VARCHAR(1), ano INTEGER)
	RETURNS TABLE (titulo VARCHAR(100), janeiro numeric(18,2), fevereiro numeric(18,2), marco numeric(18,2), abril numeric(18,2),
		maio numeric(18,2), junho numeric(18,2), julho numeric(18,2), agosto numeric(18,2), setembro numeric(18,2),
			outubro numeric(18,2), novembro numeric(18,2), dezembro numeric(18,2)) AS
$func$
SELECT f.titulo,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 1 AND f1.id = f.id)) AS janeiro,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 2 AND f1.id = f.id)) AS fevereiro,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 3 AND f1.id = f.id)) AS marco,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 4 AND f1.id = f.id)) AS abril,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 5 AND f1.id = f.id)) AS maio,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 6 AND f1.id = f.id)) AS junho,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 7 AND f1.id = f.id)) AS julho,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 8 AND f1.id = f.id)) AS agosto,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 9 AND f1.id = f.id)) AS setembro,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 10 AND f1.id = f.id)) AS outubro,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2 
		AND EXTRACT (MONTH FROM dataprocessamento) = 11 AND f1.id = f.id)) AS novembro,
	sum((SELECT sum(f1.valor)
		FROM fluxocaixa f1 
		WHERE  EXTRACT (YEAR FROM dataprocessamento) = $2
		AND EXTRACT (MONTH FROM dataprocessamento) = 12 AND f1.id = f.id)) AS dezembro
FROM fluxocaixa f WHERE f.tipo = $1
GROUP BY 1 ORDER BY 1
$func$ LANGUAGE sql;

select * from fluxocaixa('D', 2015);