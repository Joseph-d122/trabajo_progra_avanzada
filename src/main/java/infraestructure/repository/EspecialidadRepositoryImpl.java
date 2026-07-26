package infraestructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import domain.model.Especialidad;

@ApplicationScoped
public class EspecialidadRepositoryImpl implements PanacheRepositoryBase<Especialidad, Integer> {
    
}
