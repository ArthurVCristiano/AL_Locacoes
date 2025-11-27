require("dotenv").config();

const prod =require("./produtoService");

const clie = require("./clienteService");


const port = process.env.PORT;

const cors = require('cors');
const express = require("express");

const app = express();

app.use(cors({
     origin: "http://127.0.0.1:5500",
    methods: ["GET", "POST", "PATCH", "DELETE"]
}))

app.use(express.json());

app.get("/",(req,res)=>{
    res.json({
        message:"Funcionando"
    })
})

app.get("/produtos/:id",(req,res)=>{
    const produto = prod.selecionarProdutoUnico(req.params.id);
    res.json(produto)
})

app.get("/produtos",(req,res)=>{
    const produtos = prod.selecionarProdutos();
    res.json(produtos)
})

app.post("/produtos",(req,res)=>{
    console.log(req.body);
    prod.salvarProduto(req.body);
    res.sendStatus(201);
})

app.patch("/produtos",(req,res)=>{
    console.log(req.body);
    prod.atualizarProduto(req.params.id,req.body);
    res.sendStatus(200);
})

app.delete("/produtos/:id", (req,res)=>{
    prod.deletarProduto(req.params.id)
    res.sendStatus(200);
});

/////////////////////////////////
app.get("/clientes/:id",(req,res)=>{
    const produto = clie.selecionarClienteUnico(req.params.id);
    res.json(produto)
})

app.get("/clientes",(req,res)=>{
    const clientes = clie.selecionarClientes();
    res.json(clientes);
})

app.post("/clientes",(req,res)=>{
    console.log(req.body);
    clie.salvarCliente(req.body);
    res.sendStatus(201);
})

app.patch("/clientes",(req,res)=>{
    console.log(req.body);
    clie.atualizarCliente(req.params.id,req.body);
    res.sendStatus(200);
})

app.delete("/clientes/:id", (req,res)=>{
    clie.deletarCliente(req.params.id)
    res.sendStatus(204);
});

app.listen(port);

console.log("Backend rodando")