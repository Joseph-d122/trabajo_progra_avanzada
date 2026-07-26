package infraestructure.repository;

import domain.model.RecetaMedica;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecetaMedicaRepositoryImpl implements PanacheRepositoryBase<RecetaMedica, Integer> {
    
}
