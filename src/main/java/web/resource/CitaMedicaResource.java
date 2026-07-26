package web.resource;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import application.service.CitaMedicaReservaDTO;
import application.service.CitaMedicaService;
import domain.model.CitaMedica;

@Path("/citas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CitaMedicaResource {

    @Inject
    CitaMedicaService citaMedicaService;

    @POST
    @Path("/reservar")
    public Uni<Response> reservarCita(@Valid CitaMedicaReservaDTO dto) {
        return citaMedicaService.reservarCita(dto)
                .onItem().transform(cita -> Response.status(Response.Status.CREATED).entity(cita).build());
    }

    @GET
    public Uni<List<CitaMedica>> listarCitas() {
        return citaMedicaService.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> obtenerCitaPorId(@PathParam("id") Integer id) {
        return citaMedicaService.obtenerPorId(id)
                .onItem().ifNotNull().transform(cita -> Response.ok(cita).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> actualizarCita(@PathParam("id") Integer id, @Valid CitaMedicaReservaDTO dto) {
        return citaMedicaService.actualizar(id, dto)
                .onItem().ifNotNull().transform(cita -> Response.ok(cita).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> eliminarCita(@PathParam("id") Integer id) {
        return citaMedicaService.eliminar(id)
                .onItem().transform(v -> Response.noContent().build());
    }
}
