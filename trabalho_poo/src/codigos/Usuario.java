package codigos;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class Usuario {
    private String nome;
    private Date dataDeNascimento;
    private String login;
    private String senha;
    private String cpf;
    private String enderecoResidencia;
    private LinkedList instituicoes;
    private String cep;
    private LinkedList mensagens;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public LinkedList getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(LinkedList instituicoes) {
        this.instituicoes = instituicoes;
    }

    public LinkedList getMensagens() {
        return mensagens;
    }

    public void setMensagens(LinkedList mensagens) {
        this.mensagens = mensagens;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEnderecoResidencia() {
        return enderecoResidencia;
    }

    public void setEnderecoResidencia(String enderecoResidencia) {
        this.enderecoResidencia = enderecoResidencia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    
}
