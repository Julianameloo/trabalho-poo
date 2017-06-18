package codigos;

public class Mensagem {
    private String mensagem;
    private Usuario remetente;
    private Usuario destinatario;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mensagem() {
    }

    public Mensagem(String mensagem, Usuario remetente, Usuario destinatario) {
        this.mensagem = mensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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
