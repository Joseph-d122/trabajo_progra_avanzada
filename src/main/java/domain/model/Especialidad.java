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

    @Column(name = "especialidad_nombre")
    private String nombre;

    @Column(name = "especialidad_descripcion")
    private String descripcion;
}