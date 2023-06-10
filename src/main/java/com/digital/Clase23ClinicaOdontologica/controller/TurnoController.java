package com.digital.Clase23ClinicaOdontologica.controller;

import com.digital.Clase23ClinicaOdontologica.model.Odontologo;
import com.digital.Clase23ClinicaOdontologica.model.Paciente;
import com.digital.Clase23ClinicaOdontologica.model.Turno;
import com.digital.Clase23ClinicaOdontologica.service.OdontologoService;
import com.digital.Clase23ClinicaOdontologica.service.PacienteService;
import com.digital.Clase23ClinicaOdontologica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Turno>> traerTodosLosTurnos() {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }
    // ---------------------------solucionado-------------------------------------
    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> response;
        //tratamiento
        Paciente pacienteBuscado = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
        if (pacienteBuscado != null && odontologoBuscado !=null){
            response= ResponseEntity.ok(turnoService.guardarTurno(turno));
            return response;
        }else{
            //build solo devolvemos el codigo bad request no el body
            response= ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return response;
        }
    }
    // --------------------------------CLASE 27 MESA DE TRABAJO (ELIMINAR,ACTUALIZAR,BUSCAR POR ID)--------------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno (@PathVariable int id){
        Turno turnoBuscado =turnoService.buscarTurnoPorID(id);
        //tratamiento
       if (turnoBuscado != null){
           turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se elimino correctamente el id: "+id);

        }else{
            //build solo devolvemos el codigo bad request no el body
         return ResponseEntity.badRequest().body("Error al eliminar el turno con id: "+id);
        }

    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno (@RequestBody Turno turno){
        Turno turnoBuscado= turnoService.buscarTurnoPorID(turno.getId());
        if(turnoBuscado != null){
            if(odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId()) !=null && pacienteService.buscarPacientePorId(turno.getPaciente().getId())!=null){
              turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Se ha actualizado correctamente el turno "+ turno.getId());
            }else{
                return ResponseEntity.badRequest().body("No se ha actualizo porque paciente o odontologo no se encuentran");
            }
        }else{
            return ResponseEntity.badRequest().body("No se actuliza, no se encuentra el turno");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId (@PathVariable int id){
        Turno turnoBuscado= turnoService.buscarTurnoPorID(id);
        if(turnoBuscado!=null){
            return ResponseEntity.ok(turnoBuscado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
