# Custodia API

🚧 **Status: Em desenvolvimento** 🚧

API pessoal para **gestão financeira**, construída com foco em **modelagem de domínio**, **decisões arquiteturais conscientes** e **uso real**, evitando overengineering e soluções genéricas.

O projeto foi pensado como:
- uma ferramenta prática de uso diário
- um estudo aplicado de arquitetura backend
- um case técnico realista para portfólio

---

## 🎯 Objetivo

Centralizar e organizar **despesas e receitas** em um único sistema, permitindo análise financeira baseada em:

- datas de competência (impacto no orçamento)
- categorias funcionais
- classificação estratégica de gastos (*budget*)

O escopo é intencionalmente **enxuto**, priorizando clareza, extensibilidade e aderência ao domínio real.

---

## 🧠 Conceitos de Domínio

### Transaction

Representa um evento financeiro atômico (despesa ou receita).

Principais decisões de modelagem:
- Valores armazenados em **centavos (`long`)** para evitar problemas de precisão
- Sinal do valor define o tipo:
  - negativo → despesa
  - positivo → receita
- Separação clara entre:
  - `transaction_date`: quando o evento ocorreu
  - `accounting_date`: quando impacta o orçamento (ex: cartão de crédito)

---

### Account

Representa onde o dinheiro está (conta corrente, cartão, dinheiro, etc).

- Não possui saldo persistido
- O saldo é **sempre derivado das transações**
- Evita inconsistência e duplicação de estado

---

### Category

Classificação funcional da transação (ex: alimentação, transporte).

- Entidade independente
- Pode ser desativada sem perda de histórico
- Não carrega regras de negócio

---

### Budget

Classificação estratégica do gasto (ex: custos fixos, conforto, conhecimento).

- Representado como **enum** na transação
- Percentuais planejados são definidos via **configuração global**
- Separação clara entre:
  - evento financeiro
  - planejamento orçamentário

---

## 🗂️ Modelo de Dados (DER)

Entidades persistidas:

- `Account`
- `Category`
- `Transaction`
- `BudgetConfig` (configuração global de percentuais)

O modelo foi definido em nível **conceitual/lógico**, com foco em:
- evitar entidades artificiais
- evitar dependências desnecessárias
- permitir evolução guiada por uso real

---

## 🛠️ Tecnologias

- **Java 21**
- **Quarkus 3.x**
- **PostgreSQL 16**
- **Hibernate ORM (JPA)**
- **Flyway** (controle explícito de schema)
- **Docker / Docker Compose**
- **OpenAPI / Swagger UI**

---

## ⚙️ Infraestrutura Local

O ambiente local é totalmente reproduzível via Docker.

### Subir o banco de dados

```bash
docker compose up -d
````

## 📦 Migrações de Banco (Flyway)
As migrations estão localizadas em:
```
src/main/resources/db/migration
````

A migration inicial cria as seguintes tabelas:
* `accounts`
* `categories`
* `transactions`
* `budget_config`

O schema do banco é controlado exclusivamente pelo Flyway.
O DDL automático do Hibernate está desativado por decisão arquitetural.

## 📑 OpenAPI / Swagger

A documentação da API é gerada automaticamente.
Swagger UI:

```
http://localhost:8080/swagger
```

OpenAPI (JSON):

```
http://localhost:8080/openapi
```

## 🚧 Estado Atual

### ✅ Implementado

* Modelagem de domínio
* DER conceitual/lógico
* Infraestrutura local com Docker
* Banco de dados versionado com Flyway
* Configuração de OpenAPI

### 🚧 Em desenvolvimento
* Mapeamento JPA das entidades
* Repositórios
* Casos de uso (Application Layer)
* Endpoints REST

### 🔮 Próximos Passos
* Implementar entidades JPA (Account, Category, Transaction)
* Criar casos de uso principais:
    * criação de transações
    * listagem por período
    * resumo mensal
* Expor API REST mínima
* Evoluir análises financeiras conforme uso real

## 📌 Observações
* Sistema single-user por definição
* Sem autenticação neste estágio
* Evolução guiada por uso real, não por hipóteses

## 🧑‍💻 Autor
Desenvolvido por Lucas Siqueira.