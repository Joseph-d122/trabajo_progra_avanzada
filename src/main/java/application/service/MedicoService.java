package application.service;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import domain.model.Medico;
import infraestructure.repository.MedicoRepositoryImpl;

@ApplicationScoped
public class MedicoService {

    @Inject
    MedicoRepositoryImpl medicoRepository;

    @Transactional
    public Uni<Medico> guardar(Medico medico) {
        return Uni.createFrom().item(() -> {
            medicoRepository.persist(medico);
            return medico;
        });
    }

    public Uni<List<Medico>> listarTodos() {
        return Uni.createFrom().item(() -> medicoRepository.listAll());
    }

    public Uni<Medico> obtenerPorId(Integer id) {
        return Uni.createFrom().item(() -> medicoRepository.findById(id));
    }

    @Transactional
    public Uni<Medico> actualizar(Integer id, Medico datos) {
        return Uni.createFrom().item(() -> {
            Medico existente = medicoRepository.findById(id);
            if (existente != null) {
                existente.setCedula(datos.getCedula());
                existente.setNombre(datos.getNombre());
                existente.setApellido(datos.getApellido());
                existente.setEspecialidad(datos.getEspecialidad());
            }
            return existente;
        });
    }

    @Transactional
    public Uni<Void> eliminar(Integer id) {
        return Uni.createFrom().voidItem().invoke(() -> medicoRepository.deleteById(id));
    }
}
