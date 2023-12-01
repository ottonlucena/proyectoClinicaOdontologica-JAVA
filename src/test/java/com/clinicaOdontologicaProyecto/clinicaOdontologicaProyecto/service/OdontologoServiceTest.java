package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    Logger logger = Logger.getLogger(OdontologoServiceTest.class);
    @Autowired
    OdontologoService odontologoService;

    @Test
    @Order(1)
    public void testGuardarOdontologo(){
        logger.info("Iniciando test de Guardado");
        Odontologo odontologo=new Odontologo(444,"Otton", "Lucena");
        odontologoService.guardarOdontologo(odontologo);

        List<Odontologo> lista = odontologoService.listarOdontologos();

        //Verificamos que la lista no este vacía y contenga el odontologo guardado
        assertTrue(lista != null && lista.contains(odontologo));
    }

    @Test
    @Order(2)
    public void testActualizarOdontolgoo(){
        logger.info("Iniciando test de Actualizar");

        Odontologo odontologoInicial = new Odontologo(333, "Otton", "Lucena");
        odontologoService.guardarOdontologo(odontologoInicial);

        //Actualizamos la matricula del odontologo
        Odontologo odontologoActualizado = new Odontologo(odontologoInicial.getId(),555, "Otton", "Lucena");
        odontologoService.actualizarOdontologo(odontologoActualizado);

        //Buscamos el odontologo
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoId(odontologoInicial.getId());

        //Verificamos que este presente el odontologo buscado
        assertTrue(odontologoBuscado.isPresent());
        Odontologo odontologoEncontrado = odontologoBuscado.get();

        //Comparamos propiedad cambiada
        assertNotEquals(odontologoInicial.getMatricula(), odontologoEncontrado.getMatricula());
    }

    @Test
    @Order(3)
    public void testEliminarOdontologo(){
        logger.info("Iniciando test de Eliminar");

        Odontologo odontologoInicial = new Odontologo(333, "Otton", "Lucena");
        odontologoService.guardarOdontologo(odontologoInicial);

        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoId(odontologoInicial.getId());
        assertTrue(odontologoBuscado.isPresent());

        //Eliminamos el odontologo
        odontologoService.eliminarOdontologo(odontologoInicial.getId());

        //Verificamos que se elimino correctamente
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologoId(odontologoInicial.getId());
        assertFalse(odontologoEliminado.isPresent());

    }
}