package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import application.service.PacienteService;
import domain.model.Paciente;

@Path("/paciente")
public class PacienteResource {

    @Inject
    PacienteService pacienteService;

    @Path("/crear")
    @POST
    public Uni<Response> crearPaciente(Paciente paciente) {
        return pacienteService.guardar(paciente)
                .onItem().transform(p -> Response.status(Response.Status.CREATED).entity(p).build());
    }

    @Path("/listar")
    @GET
    public Uni<List<Paciente>> listarPacientes() {
        return pacienteService.listarTodos();
    }

    @Path("/pacientePorId/{id}")
    @GET
    public Uni<Response> obtenerPaciente(@PathParam("id") Integer id) {
        return pacienteService.obtenerPorId(id)
                .onItem().ifNotNull().transform(p -> Response.ok(p).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/actualizarPorId/{id}")
    @PUT
    public Uni<Response> actualizarPaciente(@PathParam("id") Integer id, Paciente paciente) {
        return pacienteService.actualizar(id, paciente)
                .onItem().ifNotNull().transform(p -> Response.ok(p).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/eliminarPorId/{id}")
    @DELETE
    public Uni<Response> eliminarPaciente(@PathParam("id") Integer id) {
        return pacienteService.eliminar(id)
                .onItem().transform(v -> Response.noContent().build());
    }
}
