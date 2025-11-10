package br.com.hcfmusp.Service;

import br.com.hcfmusp.DTO.ConsultaDTO;
import br.com.hcfmusp.DTO.PacienteDTO;
import br.com.hcfmusp.Repository.ConsultaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository consultaRepository;


    public void inserirConsulta(ConsultaDTO consultaDTO) throws SQLException {
        validaConsulta(consultaDTO);
        consultaRepository.inserirConsultaDB(consultaDTO);
    }

    public ConsultaDTO verConsulta(int idPaciente) throws SQLException {

        if (idPaciente <= 0) {
            throw new IllegalArgumentException("É necessário um ID válido");
        }

        ConsultaDTO consultaDTO = consultaRepository.verificarConsultaBD(idPaciente);

        if (consultaDTO == null){
            throw new IllegalArgumentException("ID inválido.");
        }

        return consultaDTO;

    }

    public void deletarConsulta(int idPaciente) throws SQLException{

        if (idPaciente <= 0) {
            throw new IllegalArgumentException("É necessário um ID válido");
        }

        ConsultaDTO consultaDTO = consultaRepository.verificarConsultaBD(idPaciente);

        if (consultaDTO == null){
            throw new IllegalArgumentException("ID incorreto");
        }

        consultaRepository.deletaConsultaBD(idPaciente);

    }

    public boolean alterarConsulta(int idPaciente, ConsultaDTO consultaDTO) throws SQLException {

        if (idPaciente <= 0){
            throw new IllegalArgumentException("Digite um ID válido.");
        }
        if (consultaDTO == null || consultaDTO.getNomeEspecialidade().isEmpty()) {
            throw new IllegalArgumentException("O nome da especialidade é obrigatório.");
        }
        if (consultaDTO == null || consultaDTO.getModalidade().isEmpty()){
            throw new IllegalArgumentException("A modalidade é obrigatória");
        }
        if (consultaDTO == null || consultaDTO.getTipoConsulta().isEmpty()) {
            throw new IllegalArgumentException("O novo tipo de consulta é obrigatório.");
        }
        if (consultaDTO == null || consultaDTO.getIdPaciente() <= 0) {
            throw new IllegalArgumentException("O novo ID precisa ser um número inteiro.");
        }

        return consultaRepository.alterarConsultaBD(idPaciente,consultaDTO);

    }





    public void validaConsulta(ConsultaDTO consultaDTO){
        if (consultaDTO==null || consultaDTO.getNomeEspecialidade().isEmpty() ){
            throw new IllegalArgumentException("Especialidade inválida!");
        }
        if (consultaDTO==null || consultaDTO.getModalidade().isEmpty()){
            throw new IllegalArgumentException("Modalidade inválida!");
        }
        if (consultaDTO==null || consultaDTO.getTipoConsulta().isEmpty()){
            throw new IllegalArgumentException("Tipo de consulta inválida!");
        }
        if (consultaDTO==null || consultaDTO.getIdPaciente() <= 0) {
            throw new IllegalArgumentException("ID inválido!");
        }
    }



}
