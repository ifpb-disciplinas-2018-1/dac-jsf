
CREATE TABLE endereco(
	rua VARCHAR(50) PRIMARY KEY,
	bairro VARCHAR(50),
	cidade VARCHAR(25)	
);

CREATE TABLE integrante(
	id serial PRIMARY KEY,
	nome VARCHAR(50),
        dataDeNascimento DATE,
	CPF VARCHAR(15),
	id_end VARCHAR(50),
        FOREIGN KEY (id_end) REFERENCES endereco(rua) ON DELETE RESTRICT	
);

CREATE TABLE banda(
	id SERIAL PRIMARY KEY,
	nomeFantasia VARCHAR(100)
);
CREATE TABLE integrante_banda(
	id_banda int,
	id_integrante int,
	FOREIGN KEY (id_banda) REFERENCES banda(id) ON DELETE RESTRICT,
	FOREIGN KEY (id_integrante) REFERENCES integrante(id) ON DELETE RESTRICT,
	PRIMARY KEY(id_banda,id_integrante)
);


INSERT INTO endereco( rua, bairro, cidade) VALUES ('Minha rua', 'Meu bairro', 'Minha cidade');
INSERT INTO endereco( rua, bairro, cidade) VALUES ('Tua rua', 'Teu bairro', 'Tua cidade');
INSERT INTO endereco( rua, bairro, cidade) VALUES ('Nossa rua', 'Nosso bairro', 'Nossa cidade');
