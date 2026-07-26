package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import application.service.RegionService;
import domain.model.Region;

@Path("/region")
public class RegionResource {

    @Inject
    RegionService regionService;

    @Path("/crear")    
    @POST
    public Uni<Response> crearRegion(Region region) {
        return regionService.guardar(region)
                .onItem().transform(r -> Response.status(Response.Status.CREATED).entity(r).build());
    }

    @Path("/listar")
    @GET
    public Uni<List<Region>> listarRegiones() {
        return regionService.listarTodas();
    }

    @Path("/eliminarPorId/{id}")
    @DELETE
    public Uni<Response> eliminarRegion(@PathParam("id") Integer id) {
        return regionService.eliminar(id)
                .onItem().transform(v -> Response.noContent().build());
    }
}
