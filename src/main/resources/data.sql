-- Dados iniciais para demonstração da API

-- Inserir contas
INSERT INTO tb_account (id, number, agency, balance, additional_limit) VALUES 
(1, '12345678-9', '0001', 2500.00, 1000.00),
(2, '98765432-1', '0001', 1800.50, 500.00),
(3, '11111111-1', '0002', 5000.00, 2000.00);

-- Inserir cartões
INSERT INTO tb_card (id, number, additional_limit) VALUES 
(1, '**** **** **** 1234', 2000.00),
(2, '**** **** **** 5678', 1500.00),
(3, '**** **** **** 9012', 3000.00);

-- Inserir usuários
INSERT INTO tb_user (id, name, account_id, card_id) VALUES 
(1, 'João Silva Santos', 1, 1),
(2, 'Maria Oliveira Costa', 2, 2),
(3, 'Carlos Eduardo Pereira', 3, 3);

-- Inserir funcionalidades
INSERT INTO tb_feature (id, icon, description) VALUES 
(1, 'https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/pix.svg', 'PIX'),
(2, 'https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/pay.svg', 'Pagar Conta'),
(3, 'https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/transfer.svg', 'Transferir'),
(4, 'https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/account.svg', 'Conta Corrente'),
(5, 'https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/cards.svg', 'Cartões'),
(6, 'https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/loans.svg', 'Empréstimos');

-- Inserir notícias
INSERT INTO tb_news (id, icon, description) VALUES 
(1, 'https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/credit.svg', 'Santander tem o melhor cartão de crédito para você!'),
(2, 'https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/insurance.svg', 'Proteja seu patrimônio com nossos seguros completos'),
(3, 'https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/investment.svg', 'Invista seu dinheiro e faça ele render mais'),
(4, 'https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/loan.svg', 'Crédito pré-aprovado especial para você!');

-- As relações serão criadas automaticamente pelo JPA quando necessário
