CREATE TABLE veiculo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    ano INT NOT NULL,
    placa VARCHAR(255) NOT NULL,
    cor VARCHAR(255) NOT NULL,
    valor_diaria DECIMAL(10, 2) NOT NULL
);

CREATE TABLE pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL
);

INSERT INTO veiculo (marca, modelo, ano, placa, cor, valor_diaria) VALUES
('Toyota', 'Corolla', 2020, 'ABC-1234', 'Prata', 150.00),
('Honda', 'Civic', 2019, 'DEF-5678', 'Preto', 140.00),
('Ford', 'Focus', 2018, 'GHI-9012', 'Branco', 130.00);

INSERT INTO pessoa (nome, cpf, email, telefone) VALUES
('João Silva', '12345678900', 'joao@fiap.com.br', '11987626-758')


