package infraestructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import domain.model.Region;

@ApplicationScoped
public class RegionRepositoryImpl implements PanacheRepositoryBase<Region, Integer> {
    
}
