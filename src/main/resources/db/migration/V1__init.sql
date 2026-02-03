-- =========================
-- TABLE: accounts
-- =========================
CREATE TABLE IF NOT EXISTS accounts (
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
CREATE TABLE IF NOT EXISTS categories (
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
CREATE TABLE IF NOT EXISTS budget_config (
  budget_type VARCHAR(30) PRIMARY KEY,
  percentage INTEGER NOT NULL
);

-- =========================
-- TABLE: transactions
-- =========================
CREATE TABLE IF NOT EXISTS transactions (
  id BIGINT PRIMARY KEY,

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
CREATE INDEX IF NOT EXISTS idx_transactions_accounting_date
  ON transactions (accounting_date);

CREATE INDEX IF NOT EXISTS idx_transactions_account
  ON transactions (account_id);

CREATE INDEX IF NOT EXISTS idx_transactions_category
  ON transactions (category_id);