package infraestructure.repository;

import domain.model.Consultorio;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsultorioRepositoryImpl implements PanacheRepositoryBase<Consultorio, Integer>{

}
