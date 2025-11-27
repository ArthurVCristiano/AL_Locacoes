
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
    client.release();

    return pool.connect();
}

connect();

async function selecionarClientes(){
    const client = await connect();
    const res =await client.query("SELECT * from cliente");
    return res.rows;
}
async function selecionarClienteUnico(id){
    const client = await connect();
    const res =await client.query("SELECT * from cliente WHERE id =$1", [id]);
    return res.rows;
}

async function salvarCliente(cliente){
    const client = await connect();
    const sql = "INSERT INTO cliente (nome,email,endereco,telefone) VALUES($1,$2,$3,$4) "
    const valores = [cliente.nome,cliente.email,cliente.endereco,cliente.telefone];
    
    await client.query(sql, valores);
}

async function atualizarCliente(id, cliente){
    const client = await connect();
    const sql = "UPDATE cliente SET (nome,email,endereco,telefone) VALUES($1,$2,$3,$4) where id=$5"
    const valores = [cliente.nome,cliente.email,cliente.endereco,cliente.telefone, id];
    
    await client.query(sql, valores);
}

async function deletarCliente(id){
    const client = await connect();
    const sql = "DELETE from cliente where id=$1"
    const valores = [id];
    
    await client.query(sql, valores);
}

module.exports = {
   selecionarClientes,
   selecionarClienteUnico,
   salvarCliente,
   atualizarCliente,
   deletarCliente

}