package domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "paciente")
@Getter
@Setter
public class Paciente extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id")
    private Integer id;
    
    @Column(name = "paciente_cedula")
    private String cedula;
    
    @Column(name = "paciente_nombre")
    private String nombre;
    
    @Column(name = "paciente_apellido")
    private String apellido;

   @Column(name = "distrito_id")
    private Integer distritoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distrito_id")
    private Distrito distrito;
}
