DROP TABLE IF EXISTS formacao;

SET IGNORECASE TRUE;

CREATE TABLE formacao( 
  "NOME" varchar(60) DEFAULT NULL,
  "SEQORDNIVEL" int DEFAULT NULL,
  "CODNIVEL" varchar(60) DEFAULT NULL,
);

DROP TABLE IF EXISTS ies;

CREATE TABLE ies (
  "IDIES" int GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) ,
  "NOME" varchar(100) DEFAULT '0',
  "SIGLA" varchar(30) DEFAULT '0',
  PRIMARY KEY ("IDIES"),
);

DROP TABLE IF EXISTS pesquisador;

CREATE TABLE pesquisador(
  "IDPESQUISADOR" int GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) ,
  "KEYIES" int  DEFAULT '0',
  "NOME" varchar(100) DEFAULT '0',
  "KEYUNIDADE" int  ,
  "AREA" varchar(100),
  "SUBAREA" varchar(100),
  PRIMARY KEY ("IDPESQUISADOR"),
);

DROP TABLE IF EXISTS pesquisadorformacao;

CREATE TABLE pesquisadorformacao(
  "QUANTIDADE" int DEFAULT NULL,
  "KEYPESQUISADOR" int DEFAULT NULL,
  "KEYFORMACAO" char(10) DEFAULT NULL
);

DROP TABLE IF EXISTS producao;

CREATE TABLE producao(
  "IDPRODUCAO" int  GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1) ,
  "KEYPESQUISADOR" int  DEFAULT '0',
  "KEYTIPOPRODUCAO" int  DEFAULT '0',
  "ANO" int  DEFAULT '0',
  "VALOR" int  DEFAULT '0',
  "IDIOMA" varchar(16) DEFAULT NULL,
  "PAIS" VARCHAR (16) DEFAULT NULL,
  "DATE" date DEFAULT NULL,
  "HASH" varchar(100) ,
  "DUVIDA" int ,
  "NOME" varchar(500) ,
  PRIMARY KEY ("IDPRODUCAO"),
);

DROP TABLE IF EXISTS tipoproducao;

CREATE TABLE tipoproducao(
  "IDTIPOPRODUCAO" int GENERATED BY DEFAULT AS IDENTITY (START WITH 25 INCREMENT BY 1) ,
  "NOME" varchar(120) DEFAULT '0',
  "ABREVIACAO" varchar(4) DEFAULT NULL,
  PRIMARY KEY ("IDTIPOPRODUCAO"),
);

DROP TABLE IF EXISTS unidadeacademica;

CREATE TABLE unidadeacademica(
  "IDUNIDADE" int GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1)    ,
  "NOME" varchar(60)  ,
  "TIPO" varchar(10)  ,
  PRIMARY KEY ("IDUNIDADE")
);
