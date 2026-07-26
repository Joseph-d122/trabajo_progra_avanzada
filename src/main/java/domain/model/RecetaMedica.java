package domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "receta_medica")
@Getter
@Setter
public class RecetaMedica extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receta_id")
    private Integer id;
    
    @Column(name = "receta_descripcion", nullable = false)
    private String descripcion;
    
    @Column(name = "receta_medicamentos")
    private String medicamentos;
    
    @Column(name = "cita_id", nullable = false)
    private Integer citaId;
}