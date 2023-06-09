package DAO;

public class ConexaoPostgreSQL extends Conexao {
    @Override
    protected String getDriver() {
       // return "org.hsqldb.jdbc.JDBCDriver";
        return "org.postgresql.Driver";
    }

    @Override
    protected String getHost() {
        // return "jdbc:hsqldb:hsql://localhost/solid";
        return "jdbc:postgresql://localhost:5432/ProjetoIntegrador3B";
    }

    @Override
    protected String getUsuario() {
        //return "SA";
        return "postgres";
    }

    @Override
    protected String getSenha() {
        //return "";
        return "1";
    }
}
