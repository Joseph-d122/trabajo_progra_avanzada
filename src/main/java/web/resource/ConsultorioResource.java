package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import application.service.ConsultorioService;
import domain.model.Consultorio;

@Path("/consultorio")
public class ConsultorioResource {

    @Inject
    ConsultorioService consultorioService;

    @Path("/crear")
    @POST
    public Uni<Response> crear(Consultorio consultorio) {
        return consultorioService.guardar(consultorio).onItem().transform(c -> 
        Response.status(Response.Status.CREATED).entity(c).build());
    }

    @Path("/listar")
    @GET
    public Uni<List<Consultorio>> listar() {
        return consultorioService.listarTodos();
    }

    @Path("/consultorioId/{id}")
    @GET
    public Uni<Response> buscar(@PathParam("id") Integer id) {
        return consultorioService.obtenerPorId(id).onItem().ifNotNull().transform(c -> 
        Response.ok(c).build()).onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/actualizar/{id}")
    @PUT
    public Uni<Response> actualizar(@PathParam("id") Integer id, Consultorio consultorio) {
        return consultorioService.actualizar(id, consultorio).onItem().ifNotNull().transform(c -> 
        Response.ok(c).build()).onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/eliminar/{id}")
    @DELETE
    public Uni<Response> eliminar(@PathParam("id") Integer id) {
        return consultorioService.eliminar(id).onItem().transform(v -> 
        Response.noContent().build());
    }
}