package infraestructure.repository;

import domain.model.Paciente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PacienteRepositoryImpl implements PanacheRepositoryBase<Paciente, Integer>{

}
