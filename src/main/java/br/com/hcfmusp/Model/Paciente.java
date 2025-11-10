package br.com.hcfmusp.Model;

import java.time.*;

public class Paciente {
    private int idPaciente;
    private String nomePaciente;
    private String cpf;
    private LocalDate dtNascimento;
    private String email;
    private String senhaAcesso;
    private String telefone;


    public Paciente(int idPaciente, String nomePaciente, String cpf, LocalDate dtNascimento, String email, String senhaAcesso, String telefone) {
        this.idPaciente = idPaciente;
        this.nomePaciente = nomePaciente;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        this.email = email;
        this.senhaAcesso = senhaAcesso;
        this.telefone = telefone;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
