package com.digital.Clase23ClinicaOdontologica.controller;

import com.digital.Clase23ClinicaOdontologica.model.Paciente;
import com.digital.Clase23ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;


    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardarPaciente(paciente);
    }

    @GetMapping("/listar")
    public List<Paciente> listarPacientes(){
        return pacienteService.listarpacientes();
    }
    @DeleteMapping ("/{id}")
    public String eliminarPaciente(@PathVariable int id){
        pacienteService.eliminarPaciente(id);
        return "El paciente con ID " + id + " ha sido eliminado correctamente.";
    }
    @GetMapping ("/{id}")
    public Paciente buscarPacientePorID(@PathVariable int id){
        return pacienteService.buscarPacientePorId(id);
    }
    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){
        //consultamos si el paciente existe
        Paciente pacienteBuscado= pacienteService.buscarPacientePorId(paciente.getId());
        if(pacienteBuscado!=null){
            pacienteService.actualizarPaciente(paciente);
            return "Paciente Actualizado "+ "- "+ paciente.getNombre();
        }else{
            return "Paciente no exite ";
        }
    }


}
