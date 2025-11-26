# padariaTests
Repositório para o código da padaria para os testes em TES

TABELAS :
CREATE TABLE clientes( id_cliente SERIAL PRIMARY KEY, nome Varchar(60), cpf VARCHAR(11), telefone VARCHAR(11), pontos NUMERIC(10,2) );

CREATE TABLE produtos( id_produto SERIAL PRIMARY KEY, nome Varchar(30), preco NUMERIC(10,2), tipo VARCHAR(11), Quantidade Integer );

CREATE TABLE vendas( id_venda SERIAL PRIMARY KEY, cpf_cliente VARCHAR(11), valor_venda NUMERIC(10,2), form_pag VARCHAR(25), data_venda TIMESTAMP DEFAULT NOW(), produtos VARCHAR(255));
