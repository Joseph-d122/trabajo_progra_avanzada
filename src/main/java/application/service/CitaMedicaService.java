package application.service;

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
public class CitaMedicaService {

    @Inject
    CitaMedicaRepositoryImpl citaMedicaRepository;

    @Inject
    PacienteRepositoryImpl pacienteRepository;

    @Inject
    MedicoRepositoryImpl medicoRepository;

    @Auditable
    @Transactional
    public Uni<CitaMedica> reservarCita(CitaMedicaReservaDTO dto) {
        return Uni.createFrom().item(() -> {
            Paciente paciente = pacienteRepository.find("cedula", dto.getCedulaPaciente()).firstResult();
            if (paciente == null) {
                throw new IllegalArgumentException("No existe paciente con la cédula ingresada.");
            }

            Medico medico = medicoRepository.find("cedula", dto.getCedulaMedico()).firstResult();
            if (medico == null) {
                throw new IllegalArgumentException("No existe médico con la cédula ingresada.");
            }

            CitaMedica cita = new CitaMedica();
            cita.setPacienteId(paciente.getId());
            cita.setMedicoId(medico.getId());
            cita.setFechaCita(dto.getFechaCita());

            citaMedicaRepository.persist(cita);
            return cita;
        });
    }

    public Uni<List<CitaMedica>> listarTodas() {
        return Uni.createFrom().item(() -> citaMedicaRepository.listAll());
    }

    public Uni<CitaMedica> obtenerPorId(Integer id) {
        return Uni.createFrom().item(() -> citaMedicaRepository.findById(id));
    }

    @Transactional
    public Uni<CitaMedica> actualizar(Integer id, CitaMedicaReservaDTO dto) {
        return Uni.createFrom().item(() -> {
            CitaMedica citaExistente = citaMedicaRepository.findById(id);
            if (citaExistente != null) {
                Paciente paciente = pacienteRepository.find("cedula", dto.getCedulaPaciente()).firstResult();
                Medico medico = medicoRepository.find("cedula", dto.getCedulaMedico()).firstResult();
                
                if (paciente != null) citaExistente.setPacienteId(paciente.getId());
                if (medico != null) citaExistente.setMedicoId(medico.getId());
                citaExistente.setFechaCita(dto.getFechaCita());
            }
            return citaExistente;
        });
    }

    @Transactional
    public Uni<Void> eliminar(Integer id) {
        return Uni.createFrom().voidItem().invoke(() -> citaMedicaRepository.deleteById(id));
    }
}