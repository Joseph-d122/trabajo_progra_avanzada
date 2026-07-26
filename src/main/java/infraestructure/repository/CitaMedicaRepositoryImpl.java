package infraestructure.repository;

import java.time.LocalDate;
import java.util.List;

import domain.model.CitaMedica;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CitaMedicaRepositoryImpl implements PanacheRepositoryBase<CitaMedica, Integer> {

    public boolean existeCitaEnHorario(String cedulaMedico, LocalDate fechaCita) {
        long count = count("medico.cedula = ?1 and fechaCita = ?2", cedulaMedico, fechaCita);
        return count > 0;
    }
    
    public List<CitaMedica> buscarPorPaciente(String cedulaPaciente) {
        return list("paciente.cedula", cedulaPaciente);
    }

    public CitaMedica buscarCitaPorCedulaPaciente(String cedulaPaciente) {
        return find("paciente.cedula", cedulaPaciente).firstResult();
    }

    public CitaMedica buscarCitaPorCedulaMedico(String cedulaMedico) {
        return find("medico.cedula", cedulaMedico).firstResult();
    }

    public CitaMedica buscarCitaPorFecha(LocalDate fechaCita) {
        return find("fechaCita", fechaCita).firstResult();
    }
}
