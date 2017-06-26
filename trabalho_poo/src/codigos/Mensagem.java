package codigos;

import java.util.Date;

public class Mensagem {
    private String conteudo;
    private Usuario remetente;
    private Usuario destinatario;
    private Date dataHora;
    private int id;

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mensagem() {
    }

    public Mensagem(String mensagem, Usuario remetente, Usuario destinatario) {
        this.conteudo = mensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }
    
    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String mensagem) {
        this.conteudo = mensagem;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }
    
    
}
