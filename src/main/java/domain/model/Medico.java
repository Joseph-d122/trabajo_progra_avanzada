package domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medico")
@Getter
@Setter
public class Medico extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medico_id")
    private Integer id;
    
    @Column(name = "medico_cedula")
    private String cedula;
    
    @Column(name = "medico_nombre")
    private String nombre;
    
    @Column(name = "medico_apellido")
    private String apellido;
    
    @Column(name = "medico_especialidad")
    private String especialidad;
}
