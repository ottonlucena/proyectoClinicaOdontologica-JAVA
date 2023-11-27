package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.controller;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Odontologo;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.exception.ResorceNotFoundException;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        System.out.println(odontologo);
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoId(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo con id: " + odontologo.getId() + " actualizado.");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoId(@PathVariable Long id) {
        return odontologoService.buscarOdontologoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> elimiarOdontologoId(@PathVariable Long id)throws ResorceNotFoundException{
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoId(id);

        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo con id: " + id + " eliminado correctamente");
        }

        throw new ResorceNotFoundException("Odontologo con id: " +id + "no existe en la base de datos");
        //return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscarMatricula/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@PathVariable int matricula) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        return odontologoBuscado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
