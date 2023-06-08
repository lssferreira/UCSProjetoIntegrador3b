package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String SERVER = "localhost";
    private static final String PORT = "5432";
    private static final String NOMEBANCO = "ProjetoIntegrador3B";
    private static final String HOST = "jdbc:postgresql://" + SERVER + ":" + PORT + "/" + NOMEBANCO;
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1";
    private static Connection conexao;

    public static Connection criarConexao() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(HOST, USUARIO, SENHA);
                System.out.println("Conexão com o banco de dados estabelecida.");
            } catch (SQLException e) {
                System.out.println("Erro ao estabelecer a conexão com o banco de dados: " + e.getMessage());
            }
        }
        return conexao;
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
}
