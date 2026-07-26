package application.service;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import domain.model.RecetaMedica;
import infraestructure.repository.RecetaMedicaRepositoryImpl;

@ApplicationScoped
public class RecetaMedicaService {

    @Inject
    RecetaMedicaRepositoryImpl recetaMedicaRepository;

    @Transactional
    public Uni<RecetaMedica> guardar(RecetaMedica receta) {
        return Uni.createFrom().item(() -> {
            recetaMedicaRepository.persist(receta);
            return receta;
        });
    }

    public Uni<List<RecetaMedica>> listarTodas() {
        return Uni.createFrom().item(() -> recetaMedicaRepository.listAll());
    }

    public Uni<RecetaMedica> obtenerPorId(Integer id) {
        return Uni.createFrom().item(() -> recetaMedicaRepository.findById(id));
    }

    @Transactional
    public Uni<RecetaMedica> actualizar(Integer id, RecetaMedica datos) {
        return Uni.createFrom().item(() -> {
            RecetaMedica existente = recetaMedicaRepository.findById(id);
            if (existente != null) {
                existente.setDescripcion(datos.getDescripcion());
                existente.setMedicamentos(datos.getMedicamentos());
                existente.setCitaId(datos.getCitaId());
            }
            return existente;
        });
    }

    @Transactional
    public Uni<Void> eliminar(Integer id) {
        return Uni.createFrom().voidItem().invoke(() -> recetaMedicaRepository.deleteById(id));
    }
}
