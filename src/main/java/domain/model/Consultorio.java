package domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consultorio")
@Getter
@Setter
public class Consultorio extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultorio_id")
    private Integer id;
    
    @Column(name = "consultorio_numero", nullable = false)
    private String numero;
    
    @Column(name = "consultorio_ubicacion", nullable = false)
    private String ubicacion;
}
