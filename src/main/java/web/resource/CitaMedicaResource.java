package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import application.service.CitaMedicaService;
import domain.model.CitaMedica;

@Path("/cita")
public class CitaMedicaResource {

    @Inject
    CitaMedicaService citaMedicaService;
    
    @Path("/reservar")
    @POST
    public Uni<Response> reservarCita(CitaMedica cita) {
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
}
