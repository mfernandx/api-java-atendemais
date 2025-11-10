package br.com.hcfmusp.DTO;

public class ConsultaDTO {
    private String nomeEspecialidade;
    private String modalidade;
    private String tipoConsulta;
    private int idPaciente;

    public ConsultaDTO(String nomeEspecialidade, String modalidade, String tipoConsulta, int idPaciente) {
        this.nomeEspecialidade = nomeEspecialidade;
        this.modalidade = modalidade;
        this.tipoConsulta = tipoConsulta;
        this.idPaciente = idPaciente;
    }

    public ConsultaDTO() {
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
}
