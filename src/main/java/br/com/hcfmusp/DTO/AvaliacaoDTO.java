package br.com.hcfmusp.DTO;

public class AvaliacaoDTO {

    private String nomeUsuario;
    private String emailUsuario;
    private String mensagem;

    public AvaliacaoDTO() {
    }

    public AvaliacaoDTO(String nomeUsuario, String emailUsuario, String mensagem) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.mensagem = mensagem;
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
