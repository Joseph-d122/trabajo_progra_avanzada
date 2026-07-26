package infraestructure.repository;

import domain.model.Distrito;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DistritoRepositoryImpl implements PanacheRepositoryBase<Distrito, Integer> {

}
