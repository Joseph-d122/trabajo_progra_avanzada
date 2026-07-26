package application.service;

import domain.model.Distrito;
import infraestructure.repository.DistritoRepositoryImpl;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class DistritoService {

    @Inject
    DistritoRepositoryImpl distritoRepository;

    public List<Distrito> listarTodos() {
        return distritoRepository.listAll();
    }

    public Uni<Distrito> guardar(Distrito distrito) {
        return Uni.createFrom().item(() -> {
            distritoRepository.persist(distrito);
            return distrito;
        });
    }

    public Uni<List<Distrito>> listarTodas() {
        return Uni.createFrom().item(distritoRepository.listAll());
    }

    public Uni<Distrito> obtenerPorId(Integer id) {
        return Uni.createFrom().item(distritoRepository.findById(id));
    }

    public Uni<Distrito> actualizar(Integer id, Distrito d) {
        return Uni.createFrom().item(() -> {
            Distrito e = distritoRepository.findById(id);
            e.setNombre(d.getNombre());
            e.setNumeroDistrito(d.getNumeroDistrito());
            return e;
        });
    }

    public Uni<Void> eliminar(Integer id) {
        return Uni.createFrom().voidItem().invoke(() -> distritoRepository.deleteById(id));
    }
}
