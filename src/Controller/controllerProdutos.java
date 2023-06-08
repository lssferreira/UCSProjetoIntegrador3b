package Controller;

import DAO.Conexao;
import Model.Produtos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class controllerProdutos {

    private static Connection conexao;

    public static void ConectarDB() {
        conexao = Conexao.criarConexao();
    }

    public static void FecharConexaoDB() {
        Conexao.fecharConexao();
    }

    private static String getInsertProdutos() {
        return "insert into produto(codigo, nome, descricao) values (?, ?, ?)";
    }

    private static String getDeleteProdutos() {
        return "delete from produto where codigo =  ?";
    }
    private static String getUpdateProdutos() {
        return "update produto set nome = ?, descricao = ? where codigo =  ?";
    }
    private static String getSelectProdutos() {
        return "select codigo, nome, descricao from produto where codigo = ?";
    }

    public static void InserirProduto(Produtos produto) {

    }

    public static void RemoverProduto(Produtos produto) {
    }

    public static void AlterarProduto(Produtos produto) {
    }

    public static void BuscarProduto(Produtos produto) {
    }
}
