package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.dto.TurnoDTO;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Odontologo;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Paciente;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Turno;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    ObjectMapper mapper;

    public TurnoDTO guardarTurno(Turno turno){
       Turno turnoGuardado= turnoRepository.save(turno);
       return turnoATurnoDTO(turnoGuardado);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    /*public Turno actualizarTurno(Turno turno){
        return turnoRepository.save(turno);
    }*/

    public Turno actualizarTurno(TurnoDTO turnoDTO){
        System.out.println(turnoDTO);
        System.out.println(turnoDTOATurno(turnoDTO));
        return turnoRepository.save(turnoDTOATurno(turnoDTO));
    }

    /*public Optional<Turno> buscarTurnoPorId(Long id){
        return turnoRepository.findById(id);
    }*/

    public Optional<TurnoDTO> buscarTurnoPorId(Long id){
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if(turnoBuscado.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        return Optional.empty();
    }

    public List<TurnoDTO> buscarTodos(){
        List<Turno> turnoList = turnoRepository.findAll();
        List<TurnoDTO> turnoDTOList = new ArrayList<>();

        for (Turno turno : turnoList) {
            turnoDTOList.add(turnoATurnoDTO(turno));
        }

        return turnoDTOList;
    }


    private TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO respuesta= new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setFechaTurno(turno.getFechaTurno());
        return respuesta;

    }

    private Turno turnoDTOATurno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Odontologo odontologo=new Odontologo();
        Paciente paciente=new Paciente();
        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        turno.setFechaTurno(turnoDTO.getFechaTurno());
        turno.setId(turnoDTO.getId());
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        return turno;
    }



}
