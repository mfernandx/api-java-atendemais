package br.com.hcfmusp.Service;

import br.com.hcfmusp.DTO.AvaliacaoDTO;
import br.com.hcfmusp.DTO.PacienteDTO;
import br.com.hcfmusp.Repository.AvaliacaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;

@ApplicationScoped
public class AvaliacaoService {

    @Inject
    AvaliacaoRepository avaliacaoRepository;

    public void registrarAvaliacao(AvaliacaoDTO avaliacaoDTO) throws SQLException {
        validaAvaliacao(avaliacaoDTO);
        avaliacaoRepository.salvarAvaliacaoBD(avaliacaoDTO);
    }

    public void validaAvaliacao(AvaliacaoDTO avaliacaoDTO){
        if (avaliacaoDTO==null || avaliacaoDTO.getNomeUsuario().isEmpty() ){
            throw new IllegalArgumentException("Nome inválido!");
        }
        if (avaliacaoDTO==null || avaliacaoDTO.getEmailUsuario().isEmpty()){
            throw new IllegalArgumentException("E-mail inválido!");
        }
        if (avaliacaoDTO==null || avaliacaoDTO.getMensagem() == null){
            throw new IllegalArgumentException("Mensagem inválida!");
        }
    }
}
