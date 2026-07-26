package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import application.service.MedicoService;
import domain.model.Medico;

@Path("/medico")
public class MedicoResource {

    @Inject
    MedicoService medicoService;

    @Path("/crear")
    @POST
    public Uni<Response> crear(Medico medico) {
        return medicoService.guardar(medico).onItem().transform(m -> 
        Response.status(Response.Status.CREATED).entity(m).build());
    }

    @Path("/listar")
    @GET
    public Uni<List<Medico>> listar() {
        return medicoService.listarTodos();
    }

    @Path("/buscarPorId/{id}")
    @GET
    public Uni<Response> buscar(@PathParam("id") Integer id) {
        return medicoService.obtenerPorId(id).onItem().ifNotNull().transform(m -> 
        Response.ok(m).build()).onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/actualizarPorId/{id}")
    @PUT
    public Uni<Response> actualizar(@PathParam("id") Integer id, Medico medico) {
        return medicoService.actualizar(id, medico).onItem().ifNotNull().transform(m -> 
        Response.ok(m).build()).onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/eliminarPorId/{id}")
    @DELETE
    public Uni<Response> eliminar(@PathParam("id") Integer id) {
        return medicoService.eliminar(id).onItem().transform(v -> 
        Response.noContent().build());
    }
}
