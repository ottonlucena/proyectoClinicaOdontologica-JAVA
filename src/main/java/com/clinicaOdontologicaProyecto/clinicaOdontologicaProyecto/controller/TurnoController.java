package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.controller;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.dto.TurnoDTO;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Odontologo;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Paciente;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Turno;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.exception.BadRequestException;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.exception.ResorceNotFoundException;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service.OdontologoService;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service.PacienteService;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service.TurnoService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;
    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Operation(summary = "Agregar turno a la Base de Datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Turno agregado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Turno.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Turno no registrado.")
    })
    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = TurnoDTO.class))) @Parameter(description = "Agregar solo el id de paciente, id de odontologo y la fecha", example = "pacienteId: 1, odontologoId: 1, fechaTurno: 2023-12-31") @Valid Turno turno) throws BadRequestException{
        Optional<Paciente> pacienteOptional = pacienteService.buscarPacienteId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoOptional = odontologoService.buscarOdontologoId(turno.getOdontologo().getId());

        if(pacienteOptional.isPresent()&&odontologoOptional.isPresent()){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        if (pacienteOptional.isEmpty()){
            throw new BadRequestException("No se puede registrar turno sin Paciente");
        }
        throw new BadRequestException("No se puede registrar turno sin Odontologo");
        //return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar todos los turnos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Turnos encontrados con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TurnoDTO.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Turnos no encontrado.")
    })
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());

    }

    @Operation(summary = "Buscar turno por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Turno encontrado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TurnoDTO.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurnoPorId(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar turno por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Turno eliminado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TurnoDTO.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurnoPorId(@PathVariable Long id) throws ResorceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurnoPorId(id);

        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno con id: " + id + " eliminado.");
        }
        throw new ResorceNotFoundException("Turno con id: " + id + " no encontrado");
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo encontrar el id + " + id);
    }

    @Operation(summary = "Actualizar turno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Turno actualizado con exito.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TurnoDTO.class))
                    }),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado.")
    })
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody @Valid TurnoDTO turnoDTO){
        Optional<TurnoDTO> turnoDTOOptional = turnoService.buscarTurnoPorId(turnoDTO.getId());
        if (turnoDTOOptional.isPresent()){
            Optional<Paciente> pacienteOptional = pacienteService.buscarPacienteId(turnoDTO.getPacienteId());
            Optional<Odontologo> odontologoOptional = odontologoService.buscarOdontologoId(turnoDTO.getOdontologoId());
            if(pacienteOptional.isPresent()&&odontologoOptional.isPresent()){
                turnoService.actualizarTurno(turnoDTO);
                return ResponseEntity.ok("El turno se actualizó correctamente.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontro el turno.");
    }

}
