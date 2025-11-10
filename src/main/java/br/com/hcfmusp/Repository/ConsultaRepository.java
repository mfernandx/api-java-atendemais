package br.com.hcfmusp.Repository;

import br.com.hcfmusp.DTO.ConsultaDTO;
import br.com.hcfmusp.DTO.PacienteDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ApplicationScoped
public class ConsultaRepository {

    @Inject
    DataSource dataSource;

    public void inserirConsultaDB(ConsultaDTO consultaDTO) throws SQLException {

        String sql = "insert into t_fmusp_consulta (nm_especialidade, modalidade, tp_consulta, id_paciente) values (?,?,?,?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setString(1, consultaDTO.getNomeEspecialidade());
            preparedStatement.setString(2,consultaDTO.getModalidade());
            preparedStatement.setString(3,consultaDTO.getTipoConsulta());
            preparedStatement.setInt(4,consultaDTO.getIdPaciente());
            preparedStatement.executeUpdate();

        }
    }

    public ConsultaDTO verificarConsultaBD(int idPaciente) throws SQLException {

        String sql = "select * from t_fmusp_consulta WHERE id_paciente = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            preparedStatement.setInt(1, idPaciente);

            try (ResultSet resultSet = preparedStatement.executeQuery();){

                if (resultSet.next()){
                    ConsultaDTO consulta = new ConsultaDTO();
                    consulta.setNomeEspecialidade(resultSet.getString("nm_especialidade"));
                    consulta.setModalidade(resultSet.getString("modalidade"));
                    consulta.setTipoConsulta(resultSet.getString("tp_consulta"));
                    consulta.setIdPaciente(resultSet.getInt("id_paciente"));

                    return consulta;

                }else {
                    System.out.println("Nenhum registro encontrado pelo banco");
                }
            }
        }
        return null;
    }

    public void deletaConsultaBD(int idPaciente) throws SQLException{

        String sql ="DELETE from t_fmusp_consulta WHERE id_paciente = ?";

        try(Connection con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setInt(1,idPaciente);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas == 0){
                throw new SQLException("Não deletou.");
            }
        }
    }

    public boolean alterarConsultaBD(int idPaciente, ConsultaDTO consultaDTO) throws SQLException{

        String sql = "UPDATE t_fmusp_consulta SET nm_especialidade = ? , modalidade = ? , tp_consulta = ?, id_paciente = ? WHERE id_paciente = ?";

        try(Connection con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setString(1,consultaDTO.getNomeEspecialidade());
            preparedStatement.setString(2,consultaDTO.getModalidade());
            preparedStatement.setString(3,consultaDTO.getTipoConsulta());
            preparedStatement.setInt(4,consultaDTO.getIdPaciente());
            preparedStatement.setInt(5,idPaciente);

            int linhasAfetadas = preparedStatement.executeUpdate();
            return linhasAfetadas > 0;

        }
    }
}
