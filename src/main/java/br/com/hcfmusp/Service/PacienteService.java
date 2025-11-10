package br.com.hcfmusp.Service;

import br.com.hcfmusp.DTO.PacienteDTO;
import br.com.hcfmusp.Repository.PacienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.sql.SQLException;


@ApplicationScoped
public class PacienteService {

    @Inject
    PacienteRepository pacienteRepository;


    public void inserirPaciente(PacienteDTO pacienteDTO) throws SQLException {
        validaPaciente(pacienteDTO);
        pacienteRepository.inserirPacienteDB(pacienteDTO);
    }

    public PacienteDTO verificarPaciente(String email, String senhaAcesso) throws SQLException {

        if (email == null || email.isEmpty() || senhaAcesso == null || senhaAcesso.isEmpty()) {
            throw new IllegalArgumentException("E-mail e senha são obrigatórios.");
        }

        PacienteDTO paciente = pacienteRepository.verificarPacienteBD(email.trim(), senhaAcesso.trim());

        if (paciente == null){
            throw new IllegalArgumentException("E-mail ou senha incorretos");
        }

        return paciente;

    }

    public void removerPaciente(String email,String senhaAcesso) throws SQLException{

        if (email == null || email.isEmpty() || senhaAcesso == null || senhaAcesso.isEmpty()) {
            throw new IllegalArgumentException("E-mail e senha são obrigatórios.");
        }

        PacienteDTO paciente = pacienteRepository.verificarPacienteBD(email.trim(), senhaAcesso.trim());

        if (paciente == null){
            throw new IllegalArgumentException("E-mail ou senha incorretos");
        }

        pacienteRepository.deletarPacienteBD(email, senhaAcesso);


    }

    public boolean alterarDadosPaciente(String email, String senhaAcesso, String emailUpdate, String senhaAcessoUpdate, String telefoneUpdate) throws SQLException {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("O e-mail atual é obrigatório.");
        }
        if (senhaAcesso == null || senhaAcesso.isEmpty()){
            throw new IllegalArgumentException("A senha atual é obrigatória");
        }
        if (emailUpdate == null || emailUpdate.isEmpty()) {
            throw new IllegalArgumentException("O novo e-mail é obrigatório.");
        }
        if (senhaAcessoUpdate == null || senhaAcessoUpdate.isEmpty()) {
            throw new IllegalArgumentException("A nova senha é obrigatória.");
        }
        if (telefoneUpdate == null || telefoneUpdate.isEmpty()){
            throw new IllegalArgumentException("O novo telefone é obrigatório. ");
        }

        return pacienteRepository.alterarDadosPacienteBD(email, senhaAcesso, emailUpdate,senhaAcessoUpdate,telefoneUpdate);

    }


    public void validaPaciente(PacienteDTO pacienteDTO){
        if (pacienteDTO==null || pacienteDTO.getNomePaciente().isEmpty() ){
            throw new IllegalArgumentException("Nome inválido!");
        }
        if (pacienteDTO==null || pacienteDTO.getCpf().isEmpty()){
            throw new IllegalArgumentException("CPF inválido!");
        }
        if (pacienteDTO==null || pacienteDTO.getDtNascimento() == null){
            throw new IllegalArgumentException("Data de nascimento inválida!");
        }
        if (pacienteDTO==null || pacienteDTO.getEmail().isEmpty() ){
            throw new IllegalArgumentException("Email inválido!");
        }
        if (pacienteDTO==null || pacienteDTO.getSenhaAcesso().isEmpty()){
            throw new IllegalArgumentException("Senha inválida!");
        }
        if (pacienteDTO==null || pacienteDTO.getTelefone().isEmpty()){
            throw new IllegalArgumentException("Telefone inválido!");
        }
    }
}
