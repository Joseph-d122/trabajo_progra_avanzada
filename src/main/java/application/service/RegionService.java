package application.service;

import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import domain.model.Region;
import infraestructure.repository.RegionRepositoryImpl;

@ApplicationScoped
public class RegionService {

    @Inject
    RegionRepositoryImpl regionRepository;

    @Transactional
    public Uni<Region> guardar(Region region) {
        return Uni.createFrom().item(() -> {
            regionRepository.persist(region);
            return region;
        });
    }

    public Uni<List<Region>> listarTodas() {
        return Uni.createFrom().item(regionRepository.listAll());
    }

    public Uni<Region> obtenerPorId(Integer id) {
        return Uni.createFrom().item(regionRepository.findById(id));
    }

    @Transactional
    public Uni<Void> eliminar(Integer id) {
        return Uni.createFrom().voidItem().invoke(() -> regionRepository.deleteById(id));
    }
}
