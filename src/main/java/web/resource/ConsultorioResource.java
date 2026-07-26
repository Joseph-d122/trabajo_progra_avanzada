package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import application.service.ConsultorioService;
import domain.model.Consultorio;

@Path("/consultorios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultorioResource {

    @Inject
    ConsultorioService consultorioService;

    @POST
    public Uni<Response> crearConsultorio(Consultorio consultorio) {
        return consultorioService.guardar(consultorio)
                .onItem().transform(c -> Response.status(Response.Status.CREATED).entity(c).build());
    }

    @GET
    public Uni<List<Consultorio>> listarConsultorios() {
        return consultorioService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> obtenerConsultorio(@PathParam("id") Integer id) {
        return consultorioService.obtenerPorId(id)
                .onItem().ifNotNull().transform(c -> Response.ok(c).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> actualizarConsultorio(@PathParam("id") Integer id, Consultorio consultorio) {
        return consultorioService.actualizar(id, consultorio)
                .onItem().ifNotNull().transform(c -> Response.ok(c).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> eliminarConsultorio(@PathParam("id") Integer id) {
        return consultorioService.eliminar(id)
                .onItem().transform(v -> Response.noContent().build());
    }
}