package Model;

public class Produto {
    private int Codigo;
    private String Nome;
    private String Descricao;
    public Produto(Integer codigo, String nome, String descricao) {
        Codigo = codigo;
        Nome = nome;
        Descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("Código: %d\nNome: %s", getCodigo(),getNome());
    }

    public int getCodigo() {
        return Codigo;
    }

    public String getNome() {
        return Nome;
    }

    public String getDescricao() {
        return Descricao;
    }
}
