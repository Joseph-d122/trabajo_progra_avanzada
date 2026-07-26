package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import application.service.RecetaMedicaService;
import domain.model.RecetaMedica;

@Path("/receta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecetaMedicaResource {

    @Inject
    RecetaMedicaService recetaMedicaService;

    @POST
    public Uni<Response> crearReceta(RecetaMedica receta) {
        return recetaMedicaService.guardar(receta)
                .onItem().transform(r -> Response.status(Response.Status.CREATED).entity(r).build());
    }

    @GET
    public Uni<List<RecetaMedica>> listarRecetas() {
        return recetaMedicaService.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> obtenerReceta(@PathParam("id") Integer id) {
        return recetaMedicaService.obtenerPorId(id)
                .onItem().ifNotNull().transform(r -> Response.ok(r).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> actualizarReceta(@PathParam("id") Integer id, RecetaMedica receta) {
        return recetaMedicaService.actualizar(id, receta)
                .onItem().ifNotNull().transform(r -> Response.ok(r).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> eliminarReceta(@PathParam("id") Integer id) {
        return recetaMedicaService.eliminar(id)
                .onItem().transform(v -> Response.noContent().build());
    }
}
