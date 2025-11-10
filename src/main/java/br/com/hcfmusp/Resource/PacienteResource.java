package br.com.hcfmusp.Resource;

import br.com.hcfmusp.DTO.PacienteDTO;
import br.com.hcfmusp.Service.PacienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PacienteResource {

    @Inject
    PacienteService pacienteService;

    @POST
    public Response cadastrar (PacienteDTO pacienteDTO){
        try{
            pacienteService.inserirPaciente(pacienteDTO);
            return Response.status(Response.Status.CREATED).entity(Map.of("mensagem","Paciente cadastrado com sucesso!!")).build();

        } catch (SQLException e) {//erro de conexão com a base
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("mensagem","Erro com a base de dados!!")).build();
        }
        catch (IllegalArgumentException e) { //erro de dados na validação
            return Response.status(422).entity(Map.of("erro",e.getMessage())).build();
        }
    }


    @GET
    @Path("/login/{email}/{senhaAcesso}")
    public Response autenticar(@PathParam("email") String email, @PathParam("senhaAcesso") String senhaAcesso) {
        try {
            PacienteDTO paciente = pacienteService.verificarPaciente(email, senhaAcesso);

            return Response.ok(paciente).build();

        } catch (IllegalArgumentException e) { //(email/senha vazios)

            return Response.status(Response.Status.UNAUTHORIZED).entity(Map.of("erro", e.getMessage())).build();

        } catch (Exception e) {// erro genérico
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("erro", "Erro inesperado no servidor.")).build();
        }
    }


    @DELETE
    @Path("/excluir/{email}/{senhaAcesso}")
    public Response deletar (@PathParam("email") String email, @PathParam("senhaAcesso") String senhaAcesso){
        try{
            pacienteService.removerPaciente(email, senhaAcesso);
            return Response.status(Response.Status.OK).entity("Dado removido").build();

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        } catch (IllegalArgumentException e){
            return Response.status(422).entity(e.getMessage()).build();

        } catch (RuntimeException e) {

            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }

    }

    @PUT
    @Path("/atualizar/{email}/{senhaAcesso}/{emailUpdate}/{senhaAcessoUpdate}/{telefoneUpdate}")
    public Response atualizarPaciente(@PathParam("email") String email, @PathParam("senhaAcesso") String senhaAcesso, @PathParam("emailUpdate") String emailUpdate, @PathParam("senhaAcessoUpdate") String senhaAcessoUpdate, @PathParam("telefoneUpdate") String telefoneUpdate) {
        try {
            boolean atualizado = pacienteService.alterarDadosPaciente(email,senhaAcesso, emailUpdate, senhaAcessoUpdate, telefoneUpdate);

            if (atualizado) {
                return Response.status(Response.Status.OK).entity("Dados atualizados com sucesso!").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Paciente não encontrado ou nenhum dado alterado.").build();
            }

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("erro", e.getMessage())).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro de conexão com a base de dados.").build();
        }
    }







}
