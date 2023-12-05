package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.dto.TurnoDTO;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Domicilio;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Odontologo;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Paciente;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Turno;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {
    Logger logger = Logger.getLogger(TurnoServiceTest.class);


    @Autowired
    TurnoService turnoService;

    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    TurnoRepository turnoRepository;

    @Test
    @Order(1)
    public void testGuardarTurno(){
        logger.info("Iniciando test de Guardar turno");

        //Guardamos Paciente
        Paciente paciente= new Paciente("Otton", "Lucena", "123k", LocalDate.of(2023,11,28),new Domicilio("calle",123,"localidad","provincia"), "hola@gmail.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);

        //Guardamos Odontologo
        Odontologo odontologo=new Odontologo(444,"Otton", "Lucena");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);

        Turno turno = new Turno(pacienteGuardado, odontologoGuardado, LocalDate.now());

        System.out.println(turno);

        // Ejecutar el método de servicio para guardar el turno
        TurnoDTO result = turnoService.guardarTurno(turno);

        // Verificar el resultado
        assertEquals(turno.getPaciente().getId(), result.getPacienteId());
        assertEquals(turno.getOdontologo().getId(), result.getOdontologoId());
        assertEquals(turno.getFechaTurno(), result.getFechaTurno());

        // Verificar que el turno fue guardado en la base de datos
        Optional<Turno> turnoGuardado = turnoRepository.findById(result.getId());
        assertTrue(turnoGuardado.isPresent());
        assertEquals(turno.getPaciente().getId(), turnoGuardado.get().getPaciente().getId());
        assertEquals(turno.getOdontologo().getId(), turnoGuardado.get().getOdontologo().getId());
        assertEquals(turno.getFechaTurno(), turnoGuardado.get().getFechaTurno());

    }
    @Test
    @Order(2)
    public void testEliminarTurno(){
        logger.info("Iniciando test de Eliminar Turno");

        //Guardamos Paciente
        Paciente paciente= new Paciente("Otton", "Jose", "12345k", LocalDate.of(2023,11,28),new Domicilio("calle",123,"localidad","provincia"), "hola2@gmail.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);

        //Guardamos Odontologo
        Odontologo odontologo=new Odontologo(3244,"Otton", "Lucena");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);

        Turno turno = new Turno(pacienteGuardado, odontologoGuardado, LocalDate.now());

        System.out.println(turno);

        // Ejecutar el método de servicio para guardar el turno
        TurnoDTO result = turnoService.guardarTurno(turno);

        //verificamos que el turno guardado no contenga el ID null
        assertNotNull(result.getId());

        //Eliminarmos el turno
        turnoService.eliminarTurno(result.getId());

        //Verificamos que la lista de turnos no contenga el turno guardado
        List<TurnoDTO> turnoDTOList = turnoService.buscarTodos();
        assertFalse(turnoDTOList.contains(result));



    }


}