🚀 Desafio de Automação de API com Rest Assured + JSON Server
📌 Objetivo do Projeto

Este projeto tem como objetivo automatizar testes de API utilizando:

Rest Assured
JUnit 5
Allure Report
JSON Server
JSON Server Auth

A automação cobre os endpoints da API Restful Booker e também utiliza uma Fake API criada com JSON Server para simulação de cenários, isolamento de ambiente e validações independentes.

🌐 APIs e Documentações Utilizadas
API Oficial - Restful Booker
Restful Booker API Docs
Booking Endpoints Documentation
Fake REST API Online
My JSON Server
JSON Server Auth
json-server-auth NPM
Rest Assured
Rest Assured GitHub
Rest Assured Usage Guide
JSON Schema Generator
JSON to Schema Converter

🛠️ Tecnologias Utilizadas

Automação API
Java
Maven
Rest Assured
JUnit 5
Relatórios
Allure Report
Fake API
Node.js
JSON Server
JSON Server Auth

📁 Estrutura do Projeto

desafio-teste-de-automacao-api/
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

📚 Cobertura dos Testes
Endpoints Testados
Método	Endpoint	Descrição
POST	/auth	Gerar token
GET	/booking	Listar reservas
GET	/booking/{id}	Buscar reserva
POST	/booking	Criar reserva
PUT	/booking/{id}	Atualizar reserva
PATCH	/booking/{id}	Atualização parcial
DELETE	/booking/{id}	Remover reserva
GET	/ping	Health Check

🧪 Tipos de Testes

✅ Funcionais

CRUD completo
Status Code
Validação de Response
Persistência dos dados
Fluxo ponta a ponta

❌ Negativos

Payload inválido
Campos obrigatórios ausentes
IDs inexistentes
Token inválido
Endpoint inexistente

🔐 Segurança

Autenticação
Bearer Token
SQL Injection básico
XSS básico

📄 Contrato

Schema Validation
Tipagem dos campos
Estrutura JSON
Campos obrigatórios
⚡ Performance
Tempo de resposta
Estabilidade básica
Delay controlado

🚀 Configuração do Projeto

☕ Executando Projeto Java

Limpar dependências antigas
mvn clean
Instalar dependências
mvn clean install
Executar testes
mvn test

📊 Relatórios Allure

Gerar resultados
mvn clean test
Gerar relatório
mvn allure:serve

ou

allure serve allure-results
📈 Funcionalidades do Allure

✅ Histórico de execução
✅ Evidências
✅ Tempo de execução
✅ Status dos testes
✅ Logs detalhados
✅ Dashboard visual

📦 Configuração da Fake API
1️⃣ Inicializar Node.js
npm init -y
2️⃣ Instalar dependências
npm install -D json-server json-server-auth

ou

npm install json-server

📄 Estrutura da Fake API

fake-api/
│
├── db.json
├── routes.json
├── server.js
└── package.json

📄 Exemplo db.json

{
"$schema": "./node_modules/json-server/schema.json",

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
👤 Criar coleção users
{
"users": []
}
📄 Exemplo de Schema Inferido
JSON de Entrada
{
"lastname": "Strosin",
"firstname": "Jackie",
"totalprice": 88692.8,
"depositpaid": true,
"bookingdates": {
"checkin": "2018-01-02",
"checkout": "2018-01-03"
},
"additionalneeds": "Breakfast"
}
JSON Schema Gerado
{
"$schema": "http://json-schema.org/draft-04/schema#",
"type": "object",
"properties": {
"lastname": {
"type": "string"
},
"firstname": {
"type": "string"
},
"totalprice": {
"type": "number"
},
"depositpaid": {
"type": "boolean"
},
"bookingdates": {
"type": "object",
"properties": {
"checkin": {
"type": "string"
},
"checkout": {
"type": "string"
}
},
"required": [
"checkin",
"checkout"
]
},
"additionalneeds": {
"type": "string"
}
},
"required": [
"lastname",
"firstname",
"totalprice",
"depositpaid",
"bookingdates",
"additionalneeds"
]
}
📄 server.js
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

▶️ Executar Fake API

npm run fake-api

🔐 Autenticação Fake API

Registrar usuário
POST /register
Login
POST /login

🧪 Cenários Automatizados

AUTH
Gerar token válido
Login inválido
Campos obrigatórios
BOOKING
Criar reserva
Buscar reserva
Atualizar reserva
Atualização parcial
Remover reserva
NEGATIVOS
ID inválido
Payload inválido
Token inválido
Endpoint inexistente
HEALTH CHECK
Validar endpoint /ping

📄 Schema Validation

Localização dos Schemas
src/main/resources/
Arquivos
createBookingRequestSchema.json
createBookingResponseSchema.json

🚨 Simulação de Cenários

Delay de Resposta
app.use((req, res, next) => {
setTimeout(next, 1000)
})
Simulação de Erro 500
app.get('/error', (req, res) => {
res.status(500).json({
error: 'Erro interno'
})
})

🔄 Fluxo da Automação

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

📌 Boas Práticas Implementadas

✅ Testes independentes
✅ Massa dinâmica
✅ Reutilização de código
✅ Separação por responsabilidade
✅ Schema Validation
✅ Relatórios automatizados
✅ Fake API isolada
✅ Estrutura escalável


🚀 Melhorias Futuras

Docker
CI/CD com GitHub Actions
Testcontainers
Contract Testing
FakerJS
Paralelismo
SonarQube
Retry automático
Pipeline Jenkins
Integração com Azure DevOps

👨‍💻 Autor

Projeto desenvolvido para estudo e prática de automação de testes de API utilizando Rest Assured, JSON Server e Allure Report.



