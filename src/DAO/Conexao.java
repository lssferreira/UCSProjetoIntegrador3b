package DAO;

import java.sql.*;

public class Conexao {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String SERVER = "localhost";
    private static final String PORT = "5432";
    private static final String NOMEBANCO = "ProjetoIntegrador3B";
    private static final String HOST = "jdbc:postgresql://" + SERVER + ":" + PORT + "/" + NOMEBANCO;
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1";
    private static Connection conexao;

    private Conexao() {
        // Construtor privado para evitar instanciação direta da classe
    }

    public static Connection getConexao() {
        if (conexao == null) {
            synchronized (Conexao.class) {
                if (conexao == null) {
                    try {
                        Class.forName(DRIVER);
                        conexao = DriverManager.getConnection(HOST, USUARIO, SENHA);
                        System.out.println("Conexão com o banco de dados estabelecida.");
                    } catch (ClassNotFoundException e) {
                        System.out.println("Driver JDBC não encontrado: " + e.getMessage());
                    } catch (SQLException e) {
                        System.out.println("Erro ao estabelecer a conexão com o banco de dados: " + e.getMessage());
                    }
                }
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
            throw new RuntimeException(e);
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
}
