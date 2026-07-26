package application.service;

import java.time.LocalDate;
import java.util.List;

import application.interceptors.Auditable;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import domain.model.CitaMedica;
import domain.model.Medico;
import domain.model.Paciente;
import infraestructure.repository.CitaMedicaRepositoryImpl;
import infraestructure.repository.MedicoRepositoryImpl;
import infraestructure.repository.PacienteRepositoryImpl;

@ApplicationScoped
@Transactional
public class CitaMedicaService {

    @Inject
    CitaMedicaRepositoryImpl citaMedicaRepositoryImpl;

    @Auditable
    public Uni<CitaMedica> reservar(CitaMedica cita) {
        return Uni.createFrom().item(() -> {
            citaMedicaRepositoryImpl.persist(cita);
            return cita;
        });
    }

    public Uni<List<CitaMedica>> listarTodas() {
        return Uni.createFrom().item(citaMedicaRepositoryImpl.listAll());
    }

    public Uni<CitaMedica> obtenerPorId(Integer id) {
        return Uni.createFrom().item(citaMedicaRepositoryImpl.findById(id));
    }

    public Uni<CitaMedica> actualizar(Integer id, CitaMedica citaNuevosDatos) {
        return Uni.createFrom().item(id).map(i -> {
            CitaMedica citaExistente = citaMedicaRepositoryImpl.findById(i);
            if (citaExistente != null) {
                citaExistente.setPacienteId(citaNuevosDatos.getPacienteId());
                citaExistente.setMedicoId(citaNuevosDatos.getMedicoId());
                citaExistente.setFechaCita(citaNuevosDatos.getFechaCita());
            }
            return citaExistente;
        });
    }

    public Uni<Void> eliminar(Integer id) {
        return Uni.createFrom().voidItem().invoke(() -> citaMedicaRepositoryImpl.deleteById(id));
    }

    public Uni<CitaMedica> buscarCitaPorCedulaPaciente(String cedulaPaciente) {
        return Uni.createFrom().item(this.citaMedicaRepositoryImpl.buscarCitaPorCedulaPaciente(cedulaPaciente));
    }

    public Uni<CitaMedica> buscarCitaPorCedulaMedico(String cedulaMedico) {
        return Uni.createFrom().item(this.citaMedicaRepositoryImpl.buscarCitaPorCedulaMedico(cedulaMedico));
    }

    public Uni<CitaMedica> buscarCitaPorFecha(LocalDate fechaCita) {
        return Uni.createFrom().item(this.citaMedicaRepositoryImpl.buscarCitaPorFecha(fechaCita));
    }
}