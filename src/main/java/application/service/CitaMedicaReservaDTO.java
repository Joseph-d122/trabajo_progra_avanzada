package application.service;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CitaMedicaReservaDTO {

    @NotBlank(message = "La cédula del paciente es obligatoria")
    private String cedulaPaciente;

    @NotBlank(message = "La cédula del médico es obligatoria")
    private String cedulaMedico;

    @NotNull(message = "La fecha no puede ser nula")
    @FutureOrPresent(message = "La fecha de la cita debe ser en el futuro o presente")
    private LocalDate fechaCita;

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public String getCedulaMedico() {
        return cedulaMedico;
    }

    public void setCedulaMedico(String cedulaMedico) {
        this.cedulaMedico = cedulaMedico;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }
}