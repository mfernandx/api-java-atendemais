package br.com.hcfmusp.Model;

public class Consulta {
    private int idConsulta;
    private String nomeEspecialidade;
    private String modalidade;
    private String tipoConsulta;
    private int idPaciente;

    public Consulta(int idConsulta, String nomeEspecialidade, String modalidade, String tipoConsulta, int idPaciente) {
        this.idConsulta = idConsulta;
        this.nomeEspecialidade = nomeEspecialidade;
        this.modalidade = modalidade;
        this.tipoConsulta = tipoConsulta;
        this.idPaciente = idPaciente;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
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
