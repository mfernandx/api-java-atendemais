package br.com.hcfmusp.Resource;

import br.com.hcfmusp.DTO.AvaliacaoDTO;
import br.com.hcfmusp.DTO.PacienteDTO;
import br.com.hcfmusp.Service.AvaliacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Map;

@Path("/avaliacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AvaliacaoResource {

    @Inject
    AvaliacaoService avaliacaoService;

    @POST
    public Response registrar (AvaliacaoDTO avaliacaoDTO){
        try{
            avaliacaoService.registrarAvaliacao(avaliacaoDTO);
            return Response.status(Response.Status.CREATED).entity(Map.of("mensagem","Mensagem registrada com sucesso!!")).build();

        } catch (SQLException e) {//erro de conexão com a base
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("mensagem","Erro com a base de dados!!")).build();
        }
        catch (IllegalArgumentException e) { //erro de dados na validação
            return Response.status(422).entity(Map.of("erro",e.getMessage())).build();
        }
    }
}
