package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.controller;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Paciente;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.exception.ResorceNotFoundException;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarTodosPacientes());
    }

    @GetMapping("/buscarPaciente/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPacientePorID(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPacienteId(id));
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteId(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente con id: " + paciente.getId() + " actualizado correctamente");
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/buscar/{email}")
    public ResponseEntity<Paciente> buscarPorCorreo(@PathVariable String email){
        /*Optional<Paciente> pacienteBuscado=pacienteService.buscarPorCorreo(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        return ResponseEntity.notFound().build();*/
        return pacienteService.buscarPorCorreo(email)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPacienteId(@PathVariable Long id)throws ResorceNotFoundException{
         Optional<Paciente> pacienteBuscado=pacienteService.buscarPacienteId(id);

         if(pacienteBuscado.isPresent()){
             pacienteService.eliminarPaciente(id);
             return ResponseEntity.ok("Paciente con id: " + id + " eliminado");
         }

         throw new ResorceNotFoundException("No existe el ID: " +id + " en la base de datos");
    }








}
