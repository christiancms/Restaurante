-- Valentina Studio --
-- MySQL dump --
-- ---------------------------------------------------------
-- CREATE DATABASE "restaurante" ---------------------------
-- CREATE DATABASE IF NOT EXISTS restaurante CHARACTER SET utf8 COLLATE utf8_general_ci;
-- USE restaurante;
-- ---------------------------------------------------------
-- CREATE TABLE "marca" ------------------------------------
CREATE TABLE marca ( 
	id Int( 255 ) AUTO_INCREMENT NOT NULL,
	descricao VarChar( 255 ) NOT NULL,
        PRIMARY KEY (id) )
ENGINE = InnoDB
AUTO_INCREMENT = 1;
-- ---------------------------------------------------------
-- CREATE TABLE "unidade" ----------------------------------
CREATE TABLE unidade ( 
	id Int( 255 ) AUTO_INCREMENT NOT NULL,
	descricao VarChar( 255 ) NOT NULL,
	sigla VarChar( 255 ) NOT NULL,
        PRIMARY KEY (id) )
ENGINE = InnoDB
AUTO_INCREMENT = 1;
-- ---------------------------------------------------------
-- CREATE TABLE "grupo" ------------------------------------
CREATE TABLE grupo ( 
	id Int( 255 ) AUTO_INCREMENT NOT NULL,
	descricao VarChar( 255 ) NOT NULL,
        PRIMARY KEY (id) )
ENGINE = InnoDB
AUTO_INCREMENT = 1;
-- ---------------------------------------------------------
-- CREATE TABLE "produto" ----------------------------------
CREATE TABLE produto ( 
	id Int( 255 ) AUTO_INCREMENT NOT NULL,
	descricao VarChar( 255 ) NOT NULL,
        precoCompra Float( 9, 2 ) NOT NULL,
        dataCadastro Date NULL,
	grupo_id Int( 255 ) NOT NULL,
	marca_id Int( 255 ) NOT NULL,
	unidade_id Int( 255 ) NOT NULL,
        PRIMARY KEY (id) )
ENGINE = InnoDB
AUTO_INCREMENT = 1;

-- ---------------------------------------------------------
-- CREATE LINK "produto_fkmarca" -----------------------------------
ALTER TABLE produto
ADD CONSTRAINT produto_fkmarca FOREIGN KEY (marca_id)
REFERENCES marca(id);

-- ---------------------------------------------------------
-- CREATE LINK "produto_fkunidade" -----------------------------------
ALTER TABLE produto
ADD CONSTRAINT produto_fkunidade FOREIGN KEY (unidade_id)
REFERENCES unidade(id);

-- ---------------------------------------------------------
-- CREATE LINK "produto_fkgrupo" -----------------------------------
ALTER TABLE produto
ADD CONSTRAINT produto_fkgrupo FOREIGN KEY (grupo_id)
REFERENCES grupo(id);


INSERT INTO unidade VALUES
(1, 'Unidade', 'un'),
(2, 'Metro', 'm'),
(3, 'Quilograma', 'kg'),
(4, 'Litro', 'm'),
(5, 'Caixa', 'cx'),
(6, 'Fardo', 'fd'),
(7, 'Grama', 'g'),
(8, 'Mililitro', 'ml'),
(9, 'Miligrama', 'mg');

INSERT INTO grupo VALUES
(1, 'Bebidas'),
(2, 'Pratos Quentes'),
(3, 'Pratos Frios');

INSERT INTO marca VALUES
(1,'Arisco'),
(2,'Nestle'),
(3,'Knorr'),
(4,'Sadia'),
(5,'Coca-Cola'),
(6,'Fanta');

ALTER TABLE produtos ALTER COLUMN dataCadastro Timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

INSERT INTO produto VALUES
(1,'Refrigerante Lata',2.49,NOW(),1,5,8),
(2,'Refrigerante Lata',2.49,NOW(),1,6,8);

ALTER TABLE grupo ADD COLUMN foto BLOB NULL;

ALTER TABLE uf RENAME TO unidadefederacao;

ALTER  TABLE colaborador ADD appToken VARCHAR(200);

ALTER TABLE colaborador ADD usuario_id INT,
	ADD FOREIGN KEY FK_colaborador_usuario_id(usuario_id) REFERENCES FK_colaborador_usuario_id(id)

ALTER TABLE usuario ADD versao INT(20);
ALTER TABLE colaborador ADD versao INT(20);

	INSERT INTO status (classe, indice, opcao, versao)
VALUES
('Produto',0,'ATIVO',1),
('Produto',1,'INATIVO',1),
('Produto',2,'EMFALTA',1),
('Produto',3,'DEFASADO',1),
('Pedido',0,'APROVADO',1),
('Pedido',1,'CANCELADO',1),
('Pedido',2,'ENTREGUE',1),
('Pedido',3,'FATURADO',1),
('Pedido',4,'PENDENTE',1),
('Pedido',5,'PRONTO',1);