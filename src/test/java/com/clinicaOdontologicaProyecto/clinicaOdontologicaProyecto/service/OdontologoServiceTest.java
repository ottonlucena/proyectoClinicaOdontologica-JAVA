package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Odontologo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    OdontologoService odontologoService;

    @Test
    public void testGuardarOdontologo(){
        Odontologo odontologo=new Odontologo(444,"Otton", "Lucena");
        odontologoService.guardarOdontologo(odontologo);

        List<Odontologo> lista = odontologoService.listarOdontologos();

        //Verificamos que la lista no este vacía y contenga el odontologo guardado
        assertTrue(lista != null && lista.contains(odontologo));
    }

    @Test
    public void testActualizarOdontolgoo(){
        Odontologo odontologoInicial = new Odontologo(333, "Otton", "Lucena");
        odontologoService.guardarOdontologo(odontologoInicial);

        //Actualizamos el odontologoInicial
        Odontologo odontologoActualizado = new Odontologo(1L,555, "Otton", "Lucena");
        odontologoService.actualizarOdontologo(odontologoActualizado);

        //Buscamos el odontologo
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoId(odontologoInicial.getId());

        //Verificamos que este presente el odontologo buscado
        assertTrue(odontologoBuscado.isPresent());
        Odontologo odontologoEncontrado = odontologoBuscado.get();

        //Comparamos propiedades
        assertEquals(odontologoActualizado.getMatricula(), odontologoEncontrado.getMatricula());
        assertEquals(odontologoActualizado.getNombre(), odontologoEncontrado.getNombre());
        assertEquals(odontologoActualizado.getApellido(), odontologoEncontrado.getApellido());

    }

    @Test
    public void testEliminarOdontologo(){
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