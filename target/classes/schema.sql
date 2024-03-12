CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    anoNascimento VARCHAR(255),
    profissao VARCHAR(255),
    telefone VARCHAR(255),
    endereco VARCHAR(255)
);
