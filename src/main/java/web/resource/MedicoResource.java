package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import application.service.MedicoService;
import domain.model.Medico;

@Path("/medico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicoResource {

    @Inject
    MedicoService medicoService;

    @POST
    public Uni<Response> crearMedico(Medico medico) {
        return medicoService.guardar(medico)
                .onItem().transform(m -> Response.status(Response.Status.CREATED).entity(m).build());
    }

    @GET
    public Uni<List<Medico>> listarMedicos() {
        return medicoService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> obtenerMedico(@PathParam("id") Integer id) {
        return medicoService.obtenerPorId(id)
                .onItem().ifNotNull().transform(m -> Response.ok(m).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> actualizarMedico(@PathParam("id") Integer id, Medico medico) {
        return medicoService.actualizar(id, medico)
                .onItem().ifNotNull().transform(m -> Response.ok(m).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> eliminarMedico(@PathParam("id") Integer id) {
        return medicoService.eliminar(id)
                .onItem().transform(v -> Response.noContent().build());
    }
}
