package br.com.hcfmusp.Repository;

import br.com.hcfmusp.DTO.PacienteDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@ApplicationScoped
public class PacienteRepository {
    @Inject
    DataSource dataSource;

    public void inserirPacienteDB(PacienteDTO pacienteDTO) throws SQLException {

        String sql = "insert into t_fmusp_paciente (nomePaciente, cpf, dtNascimento, email, senha, telefone) values (?,?,?,?,?,?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setString(1, pacienteDTO.getNomePaciente());
            preparedStatement.setString(2, pacienteDTO.getCpf());
            preparedStatement.setDate(3, java.sql.Date.valueOf(pacienteDTO.getDtNascimento()));//perguntar se está certo
            preparedStatement.setString(4, pacienteDTO.getEmail());
            preparedStatement.setString(5, pacienteDTO.getSenhaAcesso());
            preparedStatement.setString(6, pacienteDTO.getTelefone());
            preparedStatement.executeUpdate();

        }
    }

    public PacienteDTO verificarPacienteBD(String email, String senhaAcesso) throws SQLException {

        String sql = "select * from t_fmusp_paciente WHERE email = ? AND senha = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setString(1, email.trim());
            preparedStatement.setString(2, senhaAcesso.trim());

            try (ResultSet resultSet = preparedStatement.executeQuery();){

                if (resultSet.next()){
                    PacienteDTO paciente = new PacienteDTO();
                    paciente.setNomePaciente(resultSet.getString("nomePaciente"));
                    paciente.setCpf(resultSet.getString("cpf"));
                    paciente.setDtNascimento(resultSet.getDate("dtNascimento").toLocalDate());
                    paciente.setEmail(resultSet.getString("email"));
                    paciente.setSenhaAcesso(resultSet.getString("senha"));
                    paciente.setTelefone(resultSet.getString("telefone"));

                    return paciente;

                }else {
                    System.out.println("Nenhum registro encontrado pelo banco");
                }

            }
        }
        return null;
    }



    public void deletarPacienteBD(String email, String senhaAcesso) throws SQLException{

        String sql ="DELETE from t_fmusp_paciente WHERE email = ? AND senha = ?";

        try(Connection con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setString(1,email);
            preparedStatement.setString(2,senhaAcesso);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas == 0){
                throw new SQLException("Não deletou.");
            }
        }
    }

    public boolean alterarDadosPacienteBD(String email, String senhaAcesso, String emailUpdate, String senhaUpdate, String telefoneUpdate) throws SQLException{

        String sql = "UPDATE t_fmusp_paciente SET email = ? , senha = ? , telefone = ? WHERE email = ? AND senha = ?";

        try(Connection con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setString(1,emailUpdate);
            preparedStatement.setString(2,senhaUpdate);
            preparedStatement.setString(3,telefoneUpdate);
            preparedStatement.setString(4,email.trim());
            preparedStatement.setString(5,senhaAcesso.trim());

            int linhasAfetadas = preparedStatement.executeUpdate();
            return linhasAfetadas > 0;

        }
    }




}

