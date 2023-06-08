package Controller;

import DAO.Conexao;
import Model.Produtos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class controllerProdutos {

    private static Connection conexao;

    public static void ConectarDB() {
        conexao = Conexao.criarConexao();
    }

    public static void FecharConexaoDB() {
        Conexao.fecharConexao();
    }

    private static String getInsertProdutos(Produtos produto) {
        return String.format("insert into produto(codigo, nome, descricao) values (%d,'%s','%s')", produto.getCodigo(), produto.getNome(), produto.getDescricao()).toString();
    }

    private static String getDeleteProdutos(Produtos produto) {
        return "delete from produto where codigo = " + produto.getCodigo();
    }

    private static String getUpdateProdutos(Produtos produto) {
        return String.format("update produto set nome = '%s', descricao = '%s' where codigo = %d;", produto.getNome(), produto.getDescricao(), produto.getCodigo()).toString();
    }

    private static String getSelectProdutos(Produtos produto) {
        return "select codigo, nome, descricao from produto where codigo = " + produto.getCodigo();
    }

    public static String InserirProduto(Produtos produto) {
        try {
            if (RetornaProduto(produto) == null) {
                Conexao.executarQuery(getInsertProdutos(produto));
                return String.format("Produto adicionado: %s", produto.toString());
            } else {
                return "Um produto com o mesmo código já existe!" + produto.toString();
            }
        } catch (Exception e) {
            return "Ocorreu um erro ao inserir o produto";
        }

    }

    public static String RemoverProduto(Produtos produto) {
        try {
            if (RetornaProduto(produto) != null) {
                Conexao.executarQuery(getDeleteProdutos(produto));
                return String.format("Produto removio: %s", produto.toString());
            } else {
                return "Não existe um produto com esse código;";
            }
        } catch (Exception e) {
            return "Ocorreu um erro ao inserir o produto";
        }
    }

    public static String AlterarProduto(Produtos produto) {

        try {
            if (RetornaProduto(produto) != null) {
                Conexao.executarQuery(getUpdateProdutos(produto));
                return String.format("Produto alteado: %s", produto.toString());
            } else {
                return "Não existe um produto com esse código;";
            }
        } catch (Exception e) {
            return "Ocorreu um erro ao inserir o produto";
        }
    }

    public static Produtos BuscarProduto(Produtos produto) {
        return RetornaProduto(produto);
    }

    public static Produtos RetornaProduto(Produtos produto) {
        try {
            ResultSet resultSet = Conexao.executarQuery(getSelectProdutos(produto));
            if (resultSet.next())
                return new Produtos(resultSet.getInt("codigo"), resultSet.getString("nome"), resultSet.getString("descricao"));
        } catch (SQLException e) {
            System.out.println("Erro ao Localizar Produto!" + e.getMessage());
        }
        return null;
    }


}
