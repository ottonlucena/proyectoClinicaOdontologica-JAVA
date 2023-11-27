package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.service;

import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.entitty.Paciente;
import com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> buscarPacienteId(Long id){
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> buscarPorCorreo(String email){
        return pacienteRepository.findByEmail(email);
    }

    public List<Paciente> listarTodosPacientes(){
        return pacienteRepository.findAll();
    }

}
