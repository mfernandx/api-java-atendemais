package br.com.hcfmusp.Model;

public class Avaliacao {
    private int idAvaliacao;
    private String nomeUsuario;
    private String emailUsuario;
    private String mensagem;

    public Avaliacao(int idAvaliacao, String nomeUsuario, String emailUsuario, String mensagem) {
        this.idAvaliacao = idAvaliacao;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.mensagem = mensagem;
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
