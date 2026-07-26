package domain.model;

import java.time.LocalDate;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cita_medica")
@Getter
@Setter
public class CitaMedica extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "generator_cita_medica_seq", sequenceName = "cita_medica_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_cita_medica_seq")
    @Column(name = "cita_id")
    private Integer id;

    @Column(name = "cita_paciente_id")
    private Integer pacienteId;

    @Column(name = "cita_medico_id")
    private Integer medicoId;

    @Column(name = "cita_fecha_cita")
    private LocalDate fechaCita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cita_paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cita_medico_id")
    private Medico medico;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado = EstadoCita.PROGRAMADO;
}