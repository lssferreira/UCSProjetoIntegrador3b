package View;

import Controller.controllerProdutos;
import Model.Produtos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CadastraProduto extends JFrame implements ActionListener {

    //region Componentes
    private JPanel fundo;
    private JPanel campos;
    private JPanel botoes;

    private JButton bBusca;
    private JButton bLimpa;
    private JButton bInsere;
    private JButton bAltera;
    private JButton bRemove;

    private JLabel lCodigo;
    private JLabel lNome;
    private JLabel lDescricao;

    private JTextField tCodigo;
    private JTextField tNome;
    private JTextField tDescricao;
    //endregion

    private Connection conn;

    public static void main(String[] args) {

        CadastraProduto c = new CadastraProduto();
        controllerProdutos.ConectarDB();
        c.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bLimpa) {
            this.acaoLimpar();
        } else if (e.getSource() == bBusca) {
            this.acaoBuscar(getProduto());
        } else if (e.getSource() == bInsere) {
            this.acaoInserir(getProduto());
        } else if (e.getSource() == bRemove) {
            this.acaoRemover(getProduto());
        } else if (e.getSource() == bAltera) {
            this.acaoAlterar(getProduto());
        }
    }

    private Produtos getProduto() {
        //TODO: ADICIONAR TRATAMENTO EXCESSAO
        return new Produtos(Integer.parseInt(this.tCodigo.getText()), this.tNome.getText(), this.tDescricao.getText());
    }

    public CadastraProduto() {

        super("Cadastro de Produtos");

        this.fundo = new JPanel(new BorderLayout());
        this.campos = new JPanel(new GridLayout(3, 2));
        this.botoes = new JPanel(new FlowLayout());

        this.lCodigo = new JLabel("Código:");
        this.lNome = new JLabel("Nome:");
        this.lDescricao = new JLabel("Descrição:");

        this.tCodigo = new JTextField(10);
        this.tNome = new JTextField(30);
        this.tDescricao = new JTextField(60);

        this.campos.add(lCodigo);
        this.campos.add(tCodigo);
        this.campos.add(lNome);
        this.campos.add(tNome);
        this.campos.add(lDescricao);
        this.campos.add(tDescricao);

        this.fundo.add(this.campos, BorderLayout.CENTER);

        this.bBusca = new JButton("Buscar");
        this.bBusca.addActionListener(this);
        this.bLimpa = new JButton("Limpar");
        this.bLimpa.addActionListener(this);
        this.bInsere = new JButton("Incluir");
        this.bInsere.addActionListener(this);
        this.bAltera = new JButton("Alterar");
        this.bAltera.addActionListener(this);
        this.bRemove = new JButton("Remover");
        this.bRemove.addActionListener(this);

        this.botoes.add(bBusca);
        this.botoes.add(bLimpa);
        this.botoes.add(bInsere);
        this.botoes.add(bAltera);
        this.botoes.add(bRemove);

        this.fundo.add(this.botoes, BorderLayout.SOUTH);

        this.getContentPane().add(this.fundo);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controllerProdutos.FecharConexaoDB();
                System.exit(0);
            }
        });

        this.setSize(500, 150);
    }

    private void acaoLimpar() {
        this.tCodigo.setText("");
        this.tNome.setText("");
        this.tDescricao.setText("");
    }

    private void acaoBuscar(Produtos produto) {
       controllerProdutos.BuscarProduto(produto);
    }

    private void acaoInserir(Produtos produto) {
        controllerProdutos.InserirProduto(produto);
    }

    private void acaoAlterar(Produtos produto) {
        controllerProdutos.AlterarProduto(produto);
    }

    private void acaoRemover(Produtos produto) {
       controllerProdutos.RemoverProduto(produto);
    }
}
