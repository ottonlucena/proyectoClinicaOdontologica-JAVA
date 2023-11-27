package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.repository;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Optional<Paciente> findByEmail(String email);

}
