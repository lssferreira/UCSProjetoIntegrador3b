package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Conexao {
    private static Connection conexao;

    protected abstract String getDriver();
    protected abstract String getHost();
    protected abstract String getUsuario();
    protected abstract String getSenha();

    protected Conexao() { }

    public static synchronized Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName(getInstance().getDriver());
                conexao = DriverManager.getConnection(getInstance().getHost(), getInstance().getUsuario(), getInstance().getSenha());
                System.out.println("Conexão com o banco de dados estabelecida.");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver JDBC não encontrado: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Erro ao estabelecer a conexão com o banco de dados: " + e.getMessage());
            }
        }
        return conexao;
    }

    public static ResultSet executarQuery(String query) {
        try {
            Statement statement = getConexao().createStatement();
            statement.execute(query);
            return statement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao executar: " + e.getMessage(), e);
        }
    }

    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public static Conexao getInstance() {
        return new ConexaoPostgreSQL();
    }
}
