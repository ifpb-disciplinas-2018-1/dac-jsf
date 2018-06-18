CREATE TABLE INTEGRANTE(
	id serial PRIMARY KEY,
	nome VARCHAR(50),
	CPF VARCHAR(15)	
);
CREATE TABLE Banda(
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