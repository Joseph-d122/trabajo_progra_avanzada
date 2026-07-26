package web.resource;

import java.util.List;

import application.service.DistritoService;
import domain.model.Distrito;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/distrito")
public class DistritoResource {

    @Inject
    DistritoService distritoService;

    @Path("/crear")    
    @POST
    public Uni<Response> crear(Distrito region) {
        return distritoService.guardar(region).onItem().transform(r ->
         Response.status(Response.Status.CREATED).entity(r).build());
    }

    @Path("/listar")
    @GET
    public Uni<List<Distrito>> listar() {
        return distritoService.listarTodas();
    }

    @Path("/eliminarPorId/{id}")
    @DELETE
    public Uni<Response> eliminar(@PathParam("id") Integer id) {
        return distritoService.eliminar(id).onItem().transform(v -> 
        Response.noContent().build());
    }
}
