CREATE 

CREATE TABLE cliente(
    id SERIAL PRIMARY KEY,
    CEP VARCHAR(50) NOT NULL,
    bairro VARCHAR (50) NOT NULL,
    numero INT, 
    rua VARCHAR(50) NOT NULL,
    dataNascimento DATE NOT NULL, 
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE locacao(
    id SERIAL PRIMARY KEY,
    quantidade INT NOT NULL, 
    rua VARCHAR(50) NOT NULL,
    numero INT, 
    bairro VARCHAR(50) NOT NULL,
    cep VARCHAR(15) NOT NULL,
    dataFinal DATE NOT NULL, 
    dataInicio DATE NOT NULL, 
    valorTotal DECIMAL(10, 2) NOT NULL,
    idTipo INT NOT NULL
);

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(30) NOT NULL,
    custoUnidade DECIMAL(10, 2) NOT NULL,
    valorLocacaoUnidade DECIMAL(10, 2) NOT NULL
);