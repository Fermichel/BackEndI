package com.digital.Clase23ClinicaOdontologica.controller;

import com.digital.Clase23ClinicaOdontologica.model.Paciente;
import com.digital.Clase23ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //---------------------POSTMAPPING CON RESPONSENTITY --------------------------------
    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        ResponseEntity<Paciente> response;
        if(paciente !=null){
            response= ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
            return response;
        }else{
            response=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return response;
        }
    }

    //@PostMapping
    //public Paciente guardarPaciente(@RequestBody Paciente paciente) {
    //    return pacienteService.guardarPaciente(paciente);
    //}

    //----------------------GET LISTAR CON RESPONSENTITY ------------------------
    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.listarpacientes();
        if(pacientes.isEmpty()){ //inEmpty verifica si la lista esta vacia
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(pacientes);
        }
    }

    //@GetMapping("/listar")
    //public List<Paciente> listarPacientes(){
    //    return pacienteService.listarpacientes();
    //}

    //----------------------DELETE PACIENTE CON RESPONSENTITY ------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id){
        Paciente pacienteBuscado =pacienteService.buscarPacientePorId(id);
        if(pacienteBuscado != null){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se elimino correctamente el paciente con id: "+ id);
        }else {
            return ResponseEntity.badRequest().body("Error al eliminar paciente con id: "+id+" no se encontró.");
        }
    }
    //@DeleteMapping ("/{id}")
    //public String eliminarPaciente(@PathVariable int id){
    //    pacienteService.eliminarPaciente(id);
    //    return "El paciente con ID " + id + " ha sido eliminado correctamente.";
    //}

    //----------------------GET BUSCAR POR ID CON RESPONSENTITY ------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id){
        Paciente pacienteBuscado= pacienteService.buscarPacientePorId(id);
        if(pacienteBuscado != null){
            return ResponseEntity.ok(pacienteBuscado);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    //@GetMapping ("/{id}")
    //public Paciente buscarPacientePorID(@PathVariable int id){
    //    return pacienteService.buscarPacientePorId(id);
    //}

    //----------------------ACTUALIZAR PACIENTE CON RESPONSENTITY ------------------------
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteBuscado = pacienteService.buscarPacientePorId(paciente.getId());
        if(pacienteBuscado != null){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizo correctamente el paciente con id: "+paciente.getId()+" Nombre: "+paciente.getNombre());
        }else{
            return ResponseEntity.badRequest().body("El paciente con id: "+paciente.getId()+" no se encuentra.");
        }
    }
    //public String actualizarPaciente(@RequestBody Paciente paciente){
        //consultamos si el paciente existe
    //    Paciente pacienteBuscado= pacienteService.buscarPacientePorId(paciente.getId());
    //    if(pacienteBuscado!=null){
    //        pacienteService.actualizarPaciente(paciente);
    //        return "Paciente Actualizado "+ "- "+ paciente.getNombre();
    //    }else{
    //        return "Paciente no exite ";
    //    }
   // }


}
