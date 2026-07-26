package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import application.service.EspecialidadService;
import domain.model.Especialidad;

@Path("/especialidad")
public class EspecialidadResource {

    @Inject
    EspecialidadService especialidadService;

    @Path("/crear")
    @POST
    public Uni<Response> crear(Especialidad especialidad) {
        return especialidadService.guardar(especialidad).onItem().transform(e -> 
        Response.status(Response.Status.CREATED).entity(e).build());
    }

    @Path("/lsitar")
    @GET
    public Uni<List<Especialidad>> listar() {
        return especialidadService.listarTodas();
    }

    @Path("/actualizar/{id}")
    @PUT
    public Uni<Response> actualizar(@PathParam("id") Integer id, Especialidad especialidad) {
        return especialidadService.actualizar(id, especialidad).onItem().transform(e -> 
        Response.ok(e).build());
    }

    @Path("/eliminarId/{id}")
    @DELETE
    public Uni<Response> eliminar(@PathParam("id") Integer id) {
        return especialidadService.eliminar(id).onItem().transform(v -> 
        Response.noContent().build());
    }
}
