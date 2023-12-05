package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.repository;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String correo);
}
