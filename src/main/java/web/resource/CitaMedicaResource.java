package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import application.service.CitaMedicaService;
import domain.model.CitaMedica;
import domain.model.EstadoCita;

@Path("/cita")
public class CitaMedicaResource {

    @Inject
    CitaMedicaService citaMedicaService;
    
    @Path("/crear")
    @POST
    public Uni<Response> crear(CitaMedica cita) {
        return citaMedicaService.reservar(cita).onItem().transform(c -> 
        Response.status(Response.Status.CREATED).entity(c).build());
    }

    @Path("/listar")
    @GET
    public Uni<List<CitaMedica>> listarCitas() {
        return citaMedicaService.listarTodas();
    }

    @Path("/buscarPorPaciente/{cedula}")
    @GET
    public Uni<CitaMedica> buscarPorPaciente(@PathParam("cedula") String cedula) {
        return citaMedicaService.buscarCitaPorCedulaPaciente(cedula);
    }

    @Path("/buscarPorMedico/{cedula}")
    @GET
    public Uni<CitaMedica> buscarPorMedico(@PathParam("cedula") String cedula) {
        return citaMedicaService.buscarCitaPorCedulaMedico(cedula);
    }

    @Path("actualizarEstado/{id}/estado")
    @PATCH
    public Response actualizarEstado(@PathParam("id") Integer id, @QueryParam("estado") EstadoCita estado) {
        return Response.ok(citaMedicaService.actualizarEstado(id, estado)).build();
    }
}
