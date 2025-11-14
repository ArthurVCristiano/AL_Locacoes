CREATE TABLE cliente(
    id SERIAL PRIMARY KEY,
    CEP VARCHAR(50) NOT NULL,
    bairro VARCHAR (50) NOT NULL,
    numero INT, 
    rua VARCHAR(50) NOT NULL,
    dataNascimento DATE NOT NULL, 
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE telefoneCliente(
    telefone VARCHAR(15) PRIMARY KEY,
    idCliente SERIAL,
    FOREIGN KEY(idCliente) REFERENCES cliente(id)
)

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

CREATE TABLE estoque(
    id SERIAL PRIMARY KEY,
    qtdProdutosAlocados INT NOT NULL,
    qtdProdutosDisponiveis INT NOT NULL, 
    qtdTtotalProdutos INT NOT NULL,
    locEstoque CHAR 
);

CREATE TABLE formaPagamento(
    formaPagamento VARCHAR(30) PRIMARY KEY
    CHECK(formaPagamento IN ('Pix', 'Cartão de crédito', 'Cartão de débito', 'Boleto')),
    parcelas INT 
);

CREATE TABLE pagamento (
    id SERIAL PRIMARY KEY,
    dataPagamento DATE NOT NULL,
    valorTotal DECIMAL(10,2) NOT NULL,
    valorFrete DECIMAL(10,2) NOT NULL,
    valorPedido DECIMAL(10,2) NOT NULL,
    formaPagamento VARCHAR (30),
    idCliente SERIAL,
    FOREIGN KEY (formaPagamento) REFERENCES formaPagamento(formaPagamento),
    FOREIGN KEY (idCliente) REFERENCES cliente(id)
);

CREATE TABLE produtoEstoque(
    idProduto SERIAL,
    idEstoque SERIAL,
    FOREIGN KEY (idProduto) REFERENCES produto(idProduto),
    FOREIGN KEY (idEstoque) REFERENCES estoque(idEstoque)
);

CREATE TABLE produtoLocacao(
    idProduto SERIAL,
    idLocacao SERIAL,
    FOREIGN KEY (idProduto) REFERENCES produto(idProduto),
    FOREIGN KEY (idLocacao) REFERENCES locacao(idLocacao)
);

CREATE TABLE clienteProduto(
    idProduto SERIAL,
    idCliente SERIAL,
    FOREIGN KEY (idProduto) REFERENCES produto(idProduto),
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
);


