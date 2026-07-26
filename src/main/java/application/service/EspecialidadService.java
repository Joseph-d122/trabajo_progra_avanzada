package application.service;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import domain.model.Especialidad;
import infraestructure.repository.EspecialidadRepositoryImpl;

@ApplicationScoped
@Transactional
public class EspecialidadService {

    @Inject
    EspecialidadRepositoryImpl especialidadRepository;

    public Uni<Especialidad> guardar(Especialidad especialidad) {
        return Uni.createFrom().item(() -> {
            especialidadRepository.persist(especialidad);
            return especialidad;
        });
    }

    public Uni<List<Especialidad>> listarTodas() {
        return Uni.createFrom().item(especialidadRepository.listAll());
    }

    public Uni<Especialidad> obtenerPorId(Integer id) {
        return Uni.createFrom().item(especialidadRepository.findById(id));
    }

    public Uni<Especialidad> actualizar(Integer id, Especialidad d) {
        return Uni.createFrom().item(() -> {
            Especialidad e = especialidadRepository.findById(id);
            e.setNombre(d.getNombre()); 
            e.setDescripcion(d.getDescripcion());
            return e;
        });
    }

    public Uni<Void> eliminar(Integer id) {
        return Uni.createFrom().voidItem().invoke(() -> especialidadRepository.deleteById(id));
    }
}
