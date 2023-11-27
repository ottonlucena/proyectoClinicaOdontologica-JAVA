package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TurnoDTO {
    private Long id;
    private Long pacienteId;
    private Long odontologoId;
    private LocalDate fechaTurno;

}
