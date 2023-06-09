package View;

import Controller.ProdutosController;
import Model.Produto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class CadastraProduto extends JFrame implements ActionListener {

    //region Componentes
    private final JPanel fundo;
    private final JPanel campos;
    private final JPanel botoes;

    private final JButton btnBusca;
    private final JButton btnLimpa;
    private final JButton btnInsere;
    private final JButton btnAltera;
    private final JButton btnRemove;

    private final JLabel lblCodigo;
    private final JLabel lblNome;
    private final JLabel lblDescricao;

    private final JTextField textCodigo;
    private final JTextField textNome;
    private final JTextField textDescricao;
    //endregion
    private static ProdutosController controller;
    public static void main(String[] args) {

        CadastraProduto c = new CadastraProduto();
        controller.conectarDB();
        c.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLimpa) {
            this.acaoLimpar();
        } else {
            try {
                var produto = getProduto();
                if (e.getSource() == btnBusca) {
                    this.acaoBuscar(produto);
                } else if (e.getSource() == btnInsere) {
                    this.acaoInserir(produto);
                } else if (e.getSource() == btnRemove) {
                    this.acaoRemover(produto);
                } else if (e.getSource() == btnAltera) {
                    this.acaoAlterar(produto);
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(this, "Produto informado incorretamente;");
            }
        }
    }

    private Produto getProduto() {
        return new Produto(Integer.parseInt(this.textCodigo.getText()), this.textNome.getText(), this.textDescricao.getText());
    }

    public CadastraProduto() {

        super("Cadastro de Produtos");

        this.fundo = new JPanel(new BorderLayout());
        this.campos = new JPanel(new GridLayout(3, 2));
        this.botoes = new JPanel(new FlowLayout());

        this.lblCodigo = new JLabel("Código:");
        this.lblNome = new JLabel("Nome:");
        this.lblDescricao = new JLabel("Descrição:");

        this.textCodigo = new JTextField(10);
        this.textNome = new JTextField(30);
        this.textDescricao = new JTextField(60);

        this.campos.add(lblCodigo);
        this.campos.add(textCodigo);

        this.campos.add(lblNome);
        this.campos.add(textNome);

        this.campos.add(lblDescricao);
        this.campos.add(textDescricao);

        this.fundo.add(this.campos, BorderLayout.CENTER);

        this.btnBusca = new JButton("Buscar");
        this.btnBusca.addActionListener(this);
        this.btnLimpa = new JButton("Limpar");
        this.btnLimpa.addActionListener(this);
        this.btnInsere = new JButton("Incluir");
        this.btnInsere.addActionListener(this);
        this.btnAltera = new JButton("Alterar");
        this.btnAltera.addActionListener(this);
        this.btnRemove = new JButton("Remover");
        this.btnRemove.addActionListener(this);
        this.btnRemove.setBackground(Color.RED);

        this.botoes.add(btnBusca);
        this.botoes.add(btnLimpa);
        this.botoes.add(btnInsere);
        this.botoes.add(btnAltera);
        this.botoes.add(btnRemove);

        this.fundo.add(this.botoes, BorderLayout.SOUTH);

        this.getContentPane().add(this.fundo);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.fecharConexaoDB();
                System.exit(0);
            }
        });

        this.setSize(500, 150);
    }

    private void acaoLimpar() {
        this.textCodigo.setText("");
        this.textNome.setText("");
        this.textDescricao.setText("");
    }

    private void acaoBuscar(Produto produto) {
        var produtoRetornado = ProdutosController.buscarProduto(produto);
        if (produtoRetornado != null)
            atualizaTextBox(produtoRetornado);
        else
            JOptionPane.showMessageDialog(this, "Nenhum produto localizado com esse código.");
    }

    private void atualizaTextBox(Produto produtoRetornado) {
        this.textCodigo.setText(String.valueOf(produtoRetornado.getCodigo()));
        this.textNome.setText(produtoRetornado.getNome());
        this.textDescricao.setText(produtoRetornado.getDescricao());
    }

    private void acaoInserir(Produto produto) {
        JOptionPane.showMessageDialog(this, controller.inserirProduto(produto));
    }

    private void acaoAlterar(Produto produto) {
        JOptionPane.showMessageDialog(this, controller.alterarProduto(produto));
    }

    private void acaoRemover(Produto produto) {
        JOptionPane.showMessageDialog(this, controller.removerProduto(produto));
        acaoLimpar();
    }
}
