# 🚀 Desafio de Automação de API com Rest Assured + JSON Server

## 📌 Objetivo do Projeto

Este projeto tem como objetivo automatizar testes de API utilizando:

- Rest Assured
- JUnit
- Allure Report
- JSON Server

A automação cobre todos os endpoints da API Restful Booker e também utiliza uma Fake API criada com JSON Server para simulação de cenários e independência de ambiente.

---

# 🛠️ Tecnologias Utilizadas

## Automação API
- Java
- Maven
- Rest Assured
- JUnit 5

## Relatórios
- Allure Report

## Fake API
- Node.js
- JSON Server
- JSON Server Auth

---

# 📁 Estrutura do Projeto

```bash
desafio-teste-de-automacao-API/
│
├── .allure/
├── .mvn/
│
├── fake-api/
│   ├── db.json
│   ├── routes.json
│   ├── server.js
│   └── package.json
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/maria/teste/
│   │   │       ├── entities/
│   │   │       └── utils/
│   │   │
│   │   └── resources/
│   │       ├── createBookingRequestSchema.json
│   │       └── createBookingResponseSchema.json
│   │
│   └── test/
│       └── java/
│           └── entities/
│               └── BookingTests.java
│
├── PlanoDeTeste.md
├── README.md
└── pom.xml
```

---

# 🌐 APIs Utilizadas

## API Oficial
```bash
https://restful-booker.herokuapp.com
```

## Fake API
```bash
http://localhost:3000
```

---

# 📚 Cobertura dos Testes

## Endpoints Testados

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

# 🧪 Tipos de Testes

## ✅ Funcionais
- CRUD completo
- Status code
- Validação de response
- Persistência dos dados

---

## ❌ Negativos
- Payload inválido
- Campos obrigatórios
- IDs inexistentes
- Token inválido

---

## 🔐 Segurança
- Autenticação
- Bearer Token
- SQL Injection básico
- XSS básico

---

## 📄 Contrato
- Schema Validation
- Tipagem dos campos
- Estrutura JSON

---

## ⚡ Performance
- Tempo de resposta
- Estabilidade básica

---

# 🚀 Configuração do Projeto

# ☕ Executando projeto Java

## Instalar dependências Maven

```bash
mvn clean install
```

---

## Executar testes

```bash
mvn test
```

---

# 📦 Configuração da Fake API

# 1️⃣ Acessar pasta fake-api

```bash
cd fake-api
```

---

# 2️⃣ Inicializar Node

```bash
npm init -y
```

---

# 3️⃣ Instalar dependências

```bash
npm install -D json-server json-server-auth
```

---

# 📄 Estrutura da Fake API

```bash
fake-api/
│
├── db.json
├── routes.json
├── server.js
└── package.json
```

---

# 📄 Exemplo db.json

```json
{
  "bookings": [
    {
      "id": 1,
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
  ],

  "users": [
    {
      "email": "admin@test.com",
      "password": "123456"
    }
  ]
}
```

---

# 📄 server.js

```js
const jsonServer = require('json-server')
const auth = require('json-server-auth')

const app = jsonServer.create()
const router = jsonServer.router('db.json')

const middlewares = jsonServer.defaults()

app.db = router.db

app.use(middlewares)
app.use(auth)
app.use(router)

app.listen(3000, () => {
  console.log('🚀 Fake API rodando na porta 3000')
})
```

---

# ▶️ Executar Fake API

```bash
npm run fake-api
```

---

# 🔐 Autenticação Fake API

## Registrar usuário

```bash
POST /register
```

---

## Login

```bash
POST /login
```

---

# 📊 Relatórios Allure

# Gerar resultados

```bash
mvn clean test
```

---

# Gerar relatório

```bash
allure serve allure-results
```

---

# 📈 Funcionalidades do Allure

✅ Histórico de execução  
✅ Evidências  
✅ Tempo de execução  
✅ Status dos testes  
✅ Logs detalhados  
✅ Dashboard visual

---

# 🧪 Cenários Automatizados

## AUTH
- Gerar token válido
- Login inválido
- Campos obrigatórios

---

## BOOKING
- Criar reserva
- Buscar reserva
- Atualizar reserva
- Atualização parcial
- Remover reserva

---

## NEGATIVOS
- ID inválido
- Payload inválido
- Token inválido
- Endpoint inexistente

---

## HEALTH CHECK
- Validar endpoint `/ping`

---

# 📄 Schema Validation

Schemas utilizados:

```bash
src/main/resources/
```

## Arquivos
- createBookingRequestSchema.json
- createBookingResponseSchema.json

---

# 🚨 Simulação de Cenários

## Delay de resposta

```js
app.use((req, res, next) => {
  setTimeout(next, 1000)
})
```

---

## Erro 500

```js
app.get('/error', (req, res) => {
  res.status(500).json({
    error: 'Erro interno'
  })
})
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

# 📌 Boas Práticas Implementadas

✅ Testes independentes  
✅ Massa dinâmica  
✅ Reutilização de código  
✅ Separação por responsabilidade  
✅ Schema Validation  
✅ Relatórios automatizados  
✅ Fake API isolada  
✅ Estrutura escalável

---

# 🚀 Melhorias Futuras

- Docker
- CI/CD GitHub Actions
- Testcontainers
- Contract Testing
- FakerJS
- Paralelismo
- SonarQube
- Retry automático

---

# 👨‍💻 Autor

Projeto desenvolvido para estudo e prática de automação de testes de API utilizando Rest Assured e JSON Server.

---

# ✅ Conclusão

Este projeto demonstra uma estrutura profissional de automação API, cobrindo testes funcionais, negativos, segurança, contrato e integração com Fake API e relatórios Allure.