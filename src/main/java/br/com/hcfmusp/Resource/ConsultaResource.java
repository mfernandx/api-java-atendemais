package br.com.hcfmusp.Resource;


import br.com.hcfmusp.DTO.ConsultaDTO;
import br.com.hcfmusp.DTO.PacienteDTO;
import br.com.hcfmusp.Service.ConsultaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Map;

@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ConsultaResource {

    @Inject
    ConsultaService consultaService;


    @POST
    public Response cadastrar (ConsultaDTO consultaDTO){
        try{
            consultaService.inserirConsulta(consultaDTO);
            return Response.status(Response.Status.CREATED).entity("Consulta registrada com sucesso!!").build();

        } catch (SQLException e) {//erro de conexão com a base
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro com a base de dados!!").build();
        }
        catch (IllegalArgumentException e) { //erro de dados na validação
            return Response.status(422).entity(Map.of("erro",e.getMessage())).build();
        }
    }


    @GET
    @Path("/{id}")
    public Response exibir(@PathParam("id") int idPaciente) {
        try {
            ConsultaDTO consultaDTO = consultaService.verConsulta(idPaciente);

            return Response.ok(consultaDTO).build();

        } catch (IllegalArgumentException e) {

            return Response.status(Response.Status.UNAUTHORIZED).entity(Map.of("erro", e.getMessage())).build();

        } catch (Exception e) {// erro genérico
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("erro", "Erro inesperado no servidor.")).build();
        }
    }

    @DELETE
    @Path("/excluir/{id}")
    public Response deletar (@PathParam("id") int idPaciente){
        try{
            consultaService.deletarConsulta(idPaciente);
            return Response.status(Response.Status.OK).entity("Consulta excluída!").build();

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        } catch (IllegalArgumentException e){
            return Response.status(422).entity(e.getMessage()).build();

        } catch (RuntimeException e) {

            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }

    }


    @PUT
    @Path("/atualizar/{id}")
    public Response atualizar(@PathParam("id") int idPaciente, ConsultaDTO consultaDTO) {
        try {
            consultaService.alterarConsulta(idPaciente,consultaDTO);
            return Response.status(Response.Status.OK)
                    .entity("Consulta atualizada com sucesso!").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(422).entity(e.getMessage()).build();
        }
    }







}
