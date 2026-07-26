package application.service;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import domain.model.Consultorio;
import infraestructure.repository.ConsultorioRepositoryImpl;

@ApplicationScoped
public class ConsultorioService {

    @Inject
    ConsultorioRepositoryImpl consultorioRepository;

    @Transactional
    public Uni<Consultorio> guardar(Consultorio consultorio) {
        return Uni.createFrom().item(() -> {
            consultorioRepository.persist(consultorio);
            return consultorio;
        });
    }

    public Uni<List<Consultorio>> listarTodos() {
        return Uni.createFrom().item(() -> consultorioRepository.listAll());
    }

    public Uni<Consultorio> obtenerPorId(Integer id) {
        return Uni.createFrom().item(() -> consultorioRepository.findById(id));
    }

    @Transactional
    public Uni<Consultorio> actualizar(Integer id, Consultorio datos) {
        return Uni.createFrom().item(() -> {
            Consultorio existente = consultorioRepository.findById(id);
            if (existente != null) {
                existente.setNumero(datos.getNumero());
                existente.setUbicacion(datos.getUbicacion());
            }
            return existente;
        });
    }

    @Transactional
    public Uni<Void> eliminar(Integer id) {
        return Uni.createFrom().voidItem().invoke(() -> consultorioRepository.deleteById(id));
    }
}
