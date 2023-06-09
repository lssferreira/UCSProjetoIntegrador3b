package Controller;

import DAO.Conexao;
import Model.Produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProdutosController {

    private static Connection conexao;

    public static void conectarDB() {
        conexao = Conexao.getConexao();
    }

    public static void fecharConexaoDB() {
        Conexao.fecharConexao();
    }

    private static String getInsertProdutos(Produto produto) {
        return String.format("insert into produto(codigo, nome, descricao) values (%d,'%s','%s')", produto.getCodigo(), produto.getNome(), produto.getDescricao()).toString();
    }

    private static String getDeleteProdutos(Produto produto) {
        return "delete from produto where codigo = " + produto.getCodigo();
    }

    private static String getUpdateProdutos(Produto produto) {
        return String.format("update produto set nome = '%s', descricao = '%s' where codigo = %d;", produto.getNome(), produto.getDescricao(), produto.getCodigo()).toString();
    }

    private static String getSelectProdutos(Produto produto) {
        return "select codigo, nome, descricao from produto where codigo = " + produto.getCodigo();
    }

    public static String inserirProduto(Produto produto) {
        try {
            if (retornaProduto(produto) == null) {
                Conexao.executarQuery(getInsertProdutos(produto));
                return String.format("Produto adicionado: %s", produto.toString());
            } else {
                return "Um produto com o mesmo código já existe! \n" + produto.toString();
            }
        } catch (Exception e) {
            return "Ocorreu um erro ao inserir o produto";
        }

    }

    public static String removerProduto(Produto produto) {
        try {
            if (retornaProduto(produto) != null) {
                Conexao.executarQuery(getDeleteProdutos(produto));
                return String.format("Produto removido: %s", produto.toString());
            } else {
                return "Não existe um produto com esse código;";
            }
        } catch (Exception e) {
            return "Ocorreu um erro ao inserir o produto";
        }
    }

    public static String alterarProduto(Produto produto) {

        try {
            if (retornaProduto(produto) != null) {
                Conexao.executarQuery(getUpdateProdutos(produto));
                return String.format("Produto alteado: %s", produto.toString());
            } else {
                return "Não existe um produto com esse código;";
            }
        } catch (Exception e) {
            return "Ocorreu um erro ao inserir o produto";
        }
    }

    public static Produto buscarProduto(Produto produto) {
        return retornaProduto(produto);
    }

    public static Produto retornaProduto(Produto produto) {
        try {
            ResultSet resultSet = Conexao.executarQuery(getSelectProdutos(produto));
            if (resultSet.next())
                return new Produto(resultSet.getInt("codigo"), resultSet.getString("nome"), resultSet.getString("descricao"));
        } catch (SQLException e) {
            System.out.println("Erro ao Localizar Produto!" + e.getMessage());
        }
        return null;
    }


}
