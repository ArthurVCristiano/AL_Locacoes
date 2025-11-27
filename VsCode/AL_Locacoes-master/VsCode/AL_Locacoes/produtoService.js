
async function connect(){
    const { Pool } = require('pg');
    const pool = new Pool({
        user: 'postgres',        
        host: 'localhost',       
        database: 'al_locacoes',   
        password: '123',   
        port: 5432  
    });
    const client = await pool.connect();
    console.log("Conexão estabelecida com sucesso")

    const res = await client.query("select now()");
    console.log(res.rows[0]);
    client.release();

    return pool.connect();
}

connect();

async function selecionarProdutos(){
    const client = await connect();
    const res =await client.query("SELECT * from produto");
    return res.rows;
}
async function selecionarProdutoUnico(id){
    const client = await connect();
    const res =await client.query("SELECT * from produto WHERE id =$1", [id]);
    return res.rows;
}

async function salvarProduto(produto){
    const client = await connect();
    const sql = "INSERT INTO produto(id, tipo,custounidade,valorlocacaounidade) VALUES($1,$2,$3,$4)"
    const valores = [produto.id,produto.tipo,produto.custounidade,produto.valorlocacaounidade];
    
    await client.query(sql, valores);
}

async function atualizarProduto(id, produto){
    const client = await connect();
    const sql = "UPDATE produto SET (tipo,custounidade,valorlocacaounidade) VALUES($1,$2,$3,$4) where id=$4"
    const valores = [produto.tipo,produto.custounidade,produto.valorlocacaounidade, id];
    
    await client.query(sql, valores);
}

async function deletarProduto(id){
    const client = await connect();
    const sql = "DELETE from produto where id=$1"
    const valores = [id];
    
    await client.query(sql, valores);
}

module.exports = {
    selecionarProdutos,
    selecionarProdutoUnico,
    salvarProduto,
    atualizarProduto,
    deletarProduto
}