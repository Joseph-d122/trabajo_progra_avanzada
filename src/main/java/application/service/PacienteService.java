package application.service;

import java.util.List;

import domain.model.Paciente;
import infraestructure.repository.PacienteRepositoryImpl;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PacienteService {

    @Inject
    PacienteRepositoryImpl pacienteRepository;

    @Transactional
    public Uni<Paciente> guardar(Paciente paciente) {
        return Uni.createFrom().item(() -> {
            pacienteRepository.persist(paciente);
            return paciente;
        });
    }

    public Uni<List<Paciente>> listarTodos() {
        return Uni.createFrom().item(() -> pacienteRepository.listAll());
    }

    public Uni<Paciente> obtenerPorId(Integer id) {
        return Uni.createFrom().item(() -> pacienteRepository.findById(id));
    }

    @Transactional
    public Uni<Paciente> actualizar(Integer id, Paciente datos) {
        return Uni.createFrom().item(() -> {
            Paciente existente = pacienteRepository.findById(id);
            if (existente != null) {
                existente.setCedula(datos.getCedula());
                existente.setNombre(datos.getNombre());
                existente.setApellido(datos.getApellido());
            }
            return existente;
        });
    }

    @Transactional
    public Uni<Void> eliminar(Integer id) {
        return Uni.createFrom().voidItem().invoke(() -> pacienteRepository.deleteById(id));
    }
}
