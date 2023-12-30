package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.controller;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Odontologo;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.exception.ResorceNotFoundException;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service.OdontologoService;
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
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @Operation(summary = "Listar todos los odontologos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontologos encontrados con exito.",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Odontologo.class))
            }),
            @ApiResponse(responseCode = "404", description = "Odontologos no encontrados.")
    })
    @GetMapping
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }

    @Operation(summary = "Crear odontologo en base de datos.")

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody @Valid Odontologo odontologo){
            return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));

    }

    @Operation(summary = "Actualizar odontologo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontologos actualizado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Odontologo.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Odontologo no actualizado.")
    })
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarOdontologo(@RequestBody @Valid Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologoId(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo con id: " + odontologo.getId() + " actualizado.");
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar odontologo por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Odontologo encontrado con exito.",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Odontologo.class))
            }),
            @ApiResponse(responseCode = "404",description = "Odontologo no encontrado.")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoId(@Parameter(description = "id de odontologo",example = "1") @PathVariable Long id) {
        return odontologoService.buscarOdontologoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar odontologo por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontologo eliminado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Odontologo.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Odontologo no encontrado.")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> elimiarOdontologoId(@Parameter(description = "id de odontologo", example = "1") @PathVariable Long id)throws ResorceNotFoundException{
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologoId(id);

        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo con id: " + id + " eliminado correctamente");
        }

        throw new ResorceNotFoundException("Odontologo con id: " +id + "no existe en la base de datos");
        //return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar odontologo por matricula.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontologo encontrado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Odontologo.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Odontologo no encontrado.")
    })
    @GetMapping("/buscarMatricula/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@Parameter(description = "mmatricula de odontologo",example = "123") @PathVariable int matricula) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorMatricula(matricula);
        return odontologoBuscado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
