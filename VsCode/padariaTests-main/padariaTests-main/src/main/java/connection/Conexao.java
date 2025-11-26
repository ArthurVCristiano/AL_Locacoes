package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author andre
 */
public class Conexao {
    private static Connection con = null;
    
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/padaria-db";
        String user = "postgres";
        String password =  "root";
        
        Connection con = DriverManager.getConnection(url,user,password);
        
        return con;
    }

    public static void fecharConexao(){
        if(con != null){
        con = null;
        }
    }
}
