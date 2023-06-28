package com.digital.Clase23ClinicaOdontologica.controller;

import com.digital.Clase23ClinicaOdontologica.entity.Paciente;
import com.digital.Clase23ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;


    //---------------------POST GUARDAR PACIENTE CON RESPONSENTITY --------------------------------
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

    //----------------------GET LISTAR PACIENTE CON RESPONSENTITY ------------------------
    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        if(pacientes.isEmpty()){ //inEmpty verifica si la lista esta vacia
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(pacientes);
        }
    }

    //----------------------DELETE PACIENTE CON RESPONSENTITY ------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se elimino correctamente el paciente con id: "+ id);
        }else {
            return ResponseEntity.badRequest().body("Error al eliminar paciente con id: "+id+" no se encontró.");
        }
    }

    //----------------------GET BUSCAR POR ID CON RESPONSENTITY ------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //----------------------ACTUALIZAR PACIENTE CON RESPONSENTITY ------------------------
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorID(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizo correctamente el paciente con id: "+paciente.getId()+" Nombre: "+paciente.getNombre());
        }else{
            return ResponseEntity.badRequest().body("El paciente con id: "+paciente.getId()+" no se encuentra.");
        }
    }
    //----------------------GET BUSCAR POR EMAIL CON RESPONSENTITY ------------------------
    @GetMapping("/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@PathVariable String email){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorCorreo(email);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else {
            return ResponseEntity.notFound().build(); //NOTFOUND = NO LO ENCONTRO
        }
    }


}
