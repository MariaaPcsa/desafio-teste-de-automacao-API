# 🚀 Plano de Testes API – Restful Booker

## 📌 Objetivo

Este documento descreve a estratégia de testes da API RESTful Booker, cobrindo testes funcionais, negativos, segurança, contrato e performance.

A automação tem como objetivo garantir:

- Qualidade da API
- Integridade dos dados
- Estabilidade das funcionalidades
- Segurança básica
- Confiabilidade dos endpoints

---

# 🌐 API Utilizada

- Base URL:
```bash
https://restful-booker.herokuapp.com
```

- Documentação:
```bash
https://restful-booker.herokuapp.com/apidoc/index.html
```

---

# 📚 Escopo dos Testes

## Endpoints Cobertos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | /auth | Gerar token |
| GET | /booking | Listar reservas |
| GET | /booking/{id} | Buscar reserva |
| POST | /booking | Criar reserva |
| PUT | /booking/{id} | Atualizar reserva |
| PATCH | /booking/{id} | Atualização parcial |
| DELETE | /booking/{id} | Remover reserva |
| GET | /ping | Health Check |

---

# 🧪 Estratégia de Testes

## ✅ Testes Funcionais

Validação de:

- Status Code
- Response Body
- Headers
- Persistência dos dados
- Fluxo CRUD completo
- Regras de negócio

---

## ❌ Testes Negativos

Validação de:

- Campos obrigatórios ausentes
- Payload inválido
- IDs inexistentes
- Token inválido
- Métodos incorretos
- Dados inconsistentes

---

## 🔐 Testes de Segurança

Validação de:

- Autenticação
- Token inválido
- Acesso sem autorização
- SQL Injection básico
- XSS básico

---

## 📄 Testes de Contrato

Validação de:

- Schema JSON
- Estrutura da resposta
- Tipagem dos campos
- Campos obrigatórios

---

## ⚡ Testes de Performance

Validação de:

- Tempo de resposta
- Estabilidade
- Requisições simultâneas

---

# 🗂️ Estrutura dos Cenários

# 🔑 AUTH

## CT01 - Gerar token válido

### Objetivo
Validar autenticação com credenciais válidas.

### Payload
```json
{
  "username": "admin",
  "password": "password123"
}
```

### Resultado Esperado
- Status Code 200
- Token retornado
- Campo `token` presente

---

## CT02 - Login inválido

### Resultado Esperado
- Usuário não autenticado
- Mensagem de erro

---

## CT03 - Campos vazios

### Resultado Esperado
- Erro de validação

---

# 📝 CREATE BOOKING

## CT04 - Criar reserva válida

### Payload
```json
{
  "firstname": "Maria",
  "lastname": "Silva",
  "totalprice": 500,
  "depositpaid": true,
  "bookingdates": {
    "checkin": "2026-05-10",
    "checkout": "2026-05-20"
  },
  "additionalneeds": "Breakfast"
}
```

### Resultado Esperado
- Status Code 200
- bookingid criado
- Dados persistidos

---

## CT05 - Criar reserva sem firstname

### Resultado Esperado
- Erro de validação

---

## CT06 - Criar reserva com tipos inválidos

### Exemplo
```json
{
  "firstname": 123
}
```

---

## CT07 - Datas inválidas

### Cenário
- checkout menor que checkin

---

# 🔍 GET BOOKING

## CT08 - Buscar reserva existente

### Resultado Esperado
- Status Code 200
- Dados corretos

---

## CT09 - Buscar reserva inexistente

### Resultado Esperado
- Status Code 404

---

## CT10 - Buscar ID inválido

### Exemplo
```bash
/booking/abc
```

---

# ✏️ UPDATE BOOKING

## CT11 - Atualizar reserva com token válido

### Resultado Esperado
- Status Code 200
- Dados atualizados

---

## CT12 - Atualizar sem token

### Resultado Esperado
- Status Code 403

---

## CT13 - Atualizar com token inválido

### Resultado Esperado
- Status Code 403

---

# 🧩 PATCH BOOKING

## CT14 - Atualização parcial válida

### Resultado Esperado
- Apenas campos enviados alterados

---

## CT15 - PATCH sem autenticação

### Resultado Esperado
- Status Code 403

---

# 🗑️ DELETE BOOKING

## CT16 - Deletar reserva válida

### Resultado Esperado
- Status Code 201

---

## CT17 - Deletar reserva inexistente

### Resultado Esperado
- Status Code 404 ou 405

---

## CT18 - DELETE sem token

### Resultado Esperado
- Status Code 403

---

# ❤️ HEALTH CHECK

## CT19 - Validar endpoint /ping

### Resultado Esperado
- Status Code 201

---

# 🔐 Cenários de Segurança

## CT20 - SQL Injection

### Payload
```json
{
  "username": "' OR 1=1 --"
}
```

---

## CT21 - XSS

### Payload
```json
{
  "firstname": "<script>alert(1)</script>"
}
```

---

## CT22 - Token inválido

### Resultado Esperado
- Acesso negado

---

# ⚡ Testes de Performance

## CT23 - Tempo de resposta

### Critério
- Menor que 2 segundos

---

## CT24 - Requisições simultâneas

### Cenário
- 50 requests simultâneos

---

# 📊 Critérios de Aceite

A API será considerada aprovada quando:

- ✅ 95% dos testes aprovados
- ✅ Nenhum bug crítico
- ✅ CRUD funcionando corretamente
- ✅ Tempo médio abaixo de 2 segundos
- ✅ Segurança mínima validada

---

# 🛠️ Ferramentas Utilizadas

## Automação
- Cypress
- Postman
- Newman

## Relatórios
- Mochawesome
- Allure Reports

## CI/CD
- GitHub Actions

---

# 📁 Estrutura Recomendada do Projeto

```bash
cypress/
├── e2e/
│   ├── auth/
│   ├── booking/
│   ├── health/
│   └── security/
│
├── fixtures/
│
├── support/
│   ├── commands.js
│   └── api-routes.js
│
└── reports/
```

---

# 🔄 Fluxo da Automação

```text
AUTH
 ↓
CREATE BOOKING
 ↓
GET BOOKING
 ↓
UPDATE BOOKING
 ↓
PATCH BOOKING
 ↓
DELETE BOOKING
 ↓
VALIDAÇÃO FINAL
```

---

# 🚨 Riscos

| Risco | Impacto |
|---|---|
| Ambiente indisponível | Alto |
| Dados compartilhados | Médio |
| Instabilidade da API | Alto |
| Mudança no contrato | Alto |

---

# 📈 Melhorias Futuras

- Testes de contrato com Swagger/OpenAPI
- Mock Server
- Testes de carga
- Pipeline DevOps completo
- Execução paralela
- Integração com SonarQube

---

# 👨‍💻 Boas Práticas QA

- Testes independentes
- Dados dinâmicos
- Reutilização de comandos
- Separação por feature
- Relatórios automatizados
- Assertions claras
- Cleanup de dados

---

# 📌 Massa de Teste

```json
{
  "firstname": "QA",
  "lastname": "Automation",
  "totalprice": 999,
  "depositpaid": true,
  "bookingdates": {
    "checkin": "2026-01-01",
    "checkout": "2026-01-10"
  },
  "additionalneeds": "Lunch"
}
```

---

# ✅ Conclusão

Este plano de testes garante cobertura funcional, negativa, segurança e qualidade da API Restful Booker, permitindo evolução contínua da automação e integração com pipelines DevOps.