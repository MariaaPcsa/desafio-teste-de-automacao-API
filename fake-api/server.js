const jsonServer = require('json-server');

const app = jsonServer.create();

const router = jsonServer.router('db.json');

const middlewares = jsonServer.defaults();

const routes = require('./routes.json');

const rewriter = jsonServer.rewriter(routes);

app.use(middlewares);

app.use(jsonServer.bodyParser);

// ROUTES CUSTOM
app.use(rewriter);

app.use(router);

app.listen(4000, () => {
    console.log('🚀 Fake API rodando na porta 4000');
});