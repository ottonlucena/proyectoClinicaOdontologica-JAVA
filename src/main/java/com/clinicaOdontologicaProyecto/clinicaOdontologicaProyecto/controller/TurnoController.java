package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.controller;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.dto.TurnoDTO;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Odontologo;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Paciente;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Turno;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.exception.BadRequestException;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service.OdontologoService;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service.PacienteService;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno) throws BadRequestException{
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

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurnoPorId(id);
        if(turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurnoPorId(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurnoPorId(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno con id: " + id + " eliminado.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo encontrar el id + " + id);
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turnoDTO){
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
