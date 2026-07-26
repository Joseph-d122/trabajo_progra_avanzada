package application.service;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import domain.model.Especialidad;
import infraestructure.repository.EspecialidadRepositoryImpl;

@ApplicationScoped
public class EspecialidadService {

    @Inject
    EspecialidadRepositoryImpl especialidadRepository;

    @Transactional
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

    @Transactional
    public Uni<Void> eliminar(Integer id) {
        return Uni.createFrom().voidItem().invoke(() -> especialidadRepository.deleteById(id));
    }
}
