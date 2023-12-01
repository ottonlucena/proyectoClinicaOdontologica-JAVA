package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.Objects;

@Data
public class TurnoDTO {
    private Long id;
    private Long pacienteId;
    private Long odontologoId;
    private LocalDate fechaTurno;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurnoDTO turnoDTO = (TurnoDTO) o;
        return Objects.equals(id, turnoDTO.id) && Objects.equals(pacienteId, turnoDTO.pacienteId) && Objects.equals(odontologoId, turnoDTO.odontologoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pacienteId, odontologoId);
    }
}
