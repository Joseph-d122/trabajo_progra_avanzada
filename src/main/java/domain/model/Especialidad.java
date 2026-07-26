package domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "especialidad")
@Getter
@Setter
public class Especialidad extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "especialidad_id")
    private Integer id;

    @Column(name = "especialidad_nombre", nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(name = "especialidad_descripcion", length = 250)
    private String descripcion;
}