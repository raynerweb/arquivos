CREATE SCHEMA IF NOT EXISTS ARQUIVOS;
CREATE TABLE ARQUIVOS.TB_TIPO_ARQUIVO
(
    COD_TIPO_ARQUIVO          BIGSERIAL    NOT NULL,
    TXT_DESCRICAO             VARCHAR(250) NOT NULL,
    TXT_CAMINHO_ARMAZENAMENTO TEXT         NOT NULL,
    CONSTRAINT PK_TIPO_ARQUIVO PRIMARY KEY (COD_TIPO_ARQUIVO)
);

CREATE TABLE ARQUIVOS.TB_ARQUIVO
(
    COD_ARQUIVO      BIGSERIAL   NOT NULL,
    TXT_CONTENT_TYPE VARCHAR(50) NOT NULL,
    TXT_NOME_ARQUIVO VARCHAR     NOT NULL,
    NUM_TAMANHO      int4        NOT NULL,
    DTH_UPLOAD       timestamp   NOT NULL,
    COD_TIPO_ARQUIVO int8        NOT NULL,
    CONSTRAINT PK_ARQUIVO PRIMARY KEY (COD_ARQUIVO),
    CONSTRAINT FK_TB_ARQUIVO_COD_TIPO_ARQUIVO FOREIGN KEY (COD_TIPO_ARQUIVO) REFERENCES ARQUIVOS.TB_TIPO_ARQUIVO (COD_TIPO_ARQUIVO)
);