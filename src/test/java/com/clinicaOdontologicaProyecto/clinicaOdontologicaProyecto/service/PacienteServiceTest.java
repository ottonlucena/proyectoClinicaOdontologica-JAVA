package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Domicilio;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Paciente;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    Logger logger = Logger.getLogger(PacienteServiceTest.class);

    @Autowired
    PacienteService pacienteService;

    @Test
    @Order(1)
    public void testGuardarPaciente() {
        logger.info("Iniciando test de Guardado");

        Paciente paciente= new Paciente("Otton", "Lucena", "123k", LocalDate.of(2023,11,28),new Domicilio("calle",123,"localidad","provincia"), "hola@gmail.com");
        pacienteService.guardarPaciente(paciente);

        //Creamos una lista de tipo lista
        List<Paciente> lista = pacienteService.listarTodosPacientes();

        //Verificamos que no este vacia
        assertNotNull(lista);

        assertTrue(lista.contains(paciente));
    }

    @Test
    @Order(2)
    public void testActualizarPaciente(){
        logger.info("Iniciando test de Actualizar");

        Paciente pacienteInicial= new Paciente("Otton", "Lucena", "23423", LocalDate.of(2023,11,28),new Domicilio("calle",123,"localidad","provincia"), "hola1@gmail.com");
        pacienteService.guardarPaciente(pacienteInicial);

        //Actualizamos paciente inciail
        Paciente pacienteActualizado = new Paciente(1L, "Jose", "Lucena", "12345E", LocalDate.of(2023,11,28),new Domicilio("calle",123,"localidad","provincia"), "chao@gmail.com");
        pacienteService.actualizarPaciente(pacienteActualizado);

        //Buscamos el paciente
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteId(pacienteInicial.getId());

        //Verificamos que este presente el Paciente buscado
        assertTrue(pacienteBuscado.isPresent());
        Paciente pacienteEncontrado = pacienteBuscado.get();

        //Comparamos propiedades
        assertNotEquals(pacienteEncontrado.getNombre(), pacienteActualizado.getNombre());
        assertNotEquals(pacienteEncontrado.getCedula(), pacienteActualizado.getCedula());

    }

    @Test
    @Order(3)
    public void testEliminarPaciente(){
        logger.info("Iniciando test de Eliminar");

        Paciente pacienteInicial= new Paciente("Otton", "Lucena", "34545", LocalDate.of(2023,11,28),new Domicilio("calle",123,"localidad","provincia"), "hola2@gmail.com");
        pacienteService.guardarPaciente(pacienteInicial);

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteId(pacienteInicial.getId());
        assertTrue(pacienteBuscado.isPresent());

        //Eliminamos el paciente
        pacienteService.eliminarPaciente(pacienteInicial.getId());

        //Verificamos que el paciente se elimino correctamente
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPacienteId(pacienteInicial.getId());
        assertFalse(pacienteEliminado.isPresent());

    }

}