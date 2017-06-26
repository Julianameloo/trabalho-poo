package codigos;

public class Instituicao {
    private String nome;
    private String endereco;
    private String tipo;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
    public Instituicao(String nome, String endereco, String tipo) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
    }

    public Instituicao() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
