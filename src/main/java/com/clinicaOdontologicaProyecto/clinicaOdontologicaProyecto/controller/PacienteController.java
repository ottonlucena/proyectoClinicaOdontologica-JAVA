package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.controller;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Paciente;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.exception.ResorceNotFoundException;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Listar todos los pacientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Pacientes encontrados con exito.",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Paciente.class))
            }),
            @ApiResponse(responseCode = "404", description = "Pacientes no encontrados.")
    })
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.listarTodosPacientes());
    }

    @Operation(summary = "Buscar paciente por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Paciente encontrado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado.")
    })
    @GetMapping("/buscarPaciente/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPacientePorID(@Parameter(description = "id de paciente",example = "1") @PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPacienteId(id));
    }

    @Operation(summary = "Agregar paciente a la Base de Datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Paciente agregado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Paciente no agregado.")
    })
    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody @Valid Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @Operation(summary = "Actualizar paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Paciente actualizado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Paciente no actualizado.")
    })
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarPaciente(@RequestBody @Valid Paciente paciente){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacienteId(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Paciente con id: " + paciente.getId() + " actualizado correctamente");
        }
        return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Buscar paciente por email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Paciente encontrado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado.")
    })
    @GetMapping("/buscar/{email}")
    public ResponseEntity<Paciente> buscarPorCorreo(@Parameter(description = "email de paciente", example = "otton@digital.com") @PathVariable String email){
        /*Optional<Paciente> pacienteBuscado=pacienteService.buscarPorCorreo(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        return ResponseEntity.notFound().build();*/
        return pacienteService.buscarPorCorreo(email)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar paciente por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Paciente eliminado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Paciente.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado.")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPacienteId(@Parameter(description = "id de paciente", example = "1") @PathVariable Long id)throws ResorceNotFoundException{
         Optional<Paciente> pacienteBuscado=pacienteService.buscarPacienteId(id);

         if(pacienteBuscado.isPresent()){
             pacienteService.eliminarPaciente(id);
             return ResponseEntity.ok("Paciente con id: " + id + " eliminado");
         }

         throw new ResorceNotFoundException("No existe el ID: " +id + " en la base de datos");
    }


}
