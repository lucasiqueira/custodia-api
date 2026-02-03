-- =====================================================
-- V1__init.sql
-- Initial database schema for Custodia API
-- =====================================================

-- =========================
-- TABLE: accounts
-- =========================
CREATE TABLE accounts (
  id UUID PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  type VARCHAR(30) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL
);

-- =========================
-- TABLE: categories
-- =========================
CREATE TABLE categories (
  id UUID PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(255),
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL
);

-- =========================
-- TABLE: budget_config
-- =========================
CREATE TABLE budget_config (
  budget_type VARCHAR(30) PRIMARY KEY,
  percentage INTEGER NOT NULL,
  display_name VARCHAR(100) NOT NULL
);

-- =========================
-- TABLE: transactions
-- =========================
CREATE TABLE transactions (
  id UUID PRIMARY KEY,

  transaction_date DATE NOT NULL,
  accounting_date DATE NOT NULL,

  description VARCHAR(255) NOT NULL,

  amount BIGINT NOT NULL,

  nature VARCHAR(20) NOT NULL,
  expense_type VARCHAR(20),
  budget VARCHAR(30),

  notes VARCHAR(500),

  account_id UUID NOT NULL,
  category_id UUID NOT NULL,

  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,

  CONSTRAINT fk_transaction_account
    FOREIGN KEY (account_id)
    REFERENCES accounts (id),

  CONSTRAINT fk_transaction_category
    FOREIGN KEY (category_id)
    REFERENCES categories (id)
);

-- =========================
-- INDEXES
-- =========================
CREATE INDEX idx_transactions_accounting_date
  ON transactions (accounting_date);

CREATE INDEX idx_transactions_account
  ON transactions (account_id);

CREATE INDEX idx_transactions_category
  ON transactions (category_id);

-- =========================
-- INITIAL DATA: budget_config
-- =========================
INSERT INTO budget_config (budget_type, percentage, display_name) VALUES
  ('FIXED_COSTS', 30, 'Custos Fixos'),
  ('KNOWLEDGE', 5, 'Conhecimento'),
  ('COMFORT', 15, 'Conforto'),
  ('PLEASURE', 10, 'Prazeres'),
  ('GOALS', 15, 'Metas'),
  ('INVESTMENTS', 25, 'Liberdade Financeira')
ON CONFLICT (budget_type) DO NOTHING;
-- 