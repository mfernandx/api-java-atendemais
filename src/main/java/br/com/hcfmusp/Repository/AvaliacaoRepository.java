package br.com.hcfmusp.Repository;

import br.com.hcfmusp.DTO.AvaliacaoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ApplicationScoped
public class AvaliacaoRepository {

    @Inject
    DataSource dataSource;

    public void salvarAvaliacaoBD (AvaliacaoDTO avaliacaoDTO) throws SQLException{

        String sql = "insert into t_fmusp_avaliacao (nomeUsuario, emailUsuario, mensagem ) values (?,?,?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sql)){

            preparedStatement.setString(1, avaliacaoDTO.getNomeUsuario());
            preparedStatement.setString(2, avaliacaoDTO.getEmailUsuario());
            preparedStatement.setString(3, avaliacaoDTO.getMensagem());

            preparedStatement.executeUpdate();

        }

    }

}
