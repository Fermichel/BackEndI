package com.digital.Clase23ClinicaOdontologica.controller;/*

import com.digital.Clase23ClinicaOdontologica.entity.Odontologo;
import com.digital.Clase23ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //---------------------POSTMAPPING CON RESPONSENTITY --------------------------------
    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<Odontologo> response;
        if(odontologo !=null){
            response= ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
            return response;
        }else{
            response=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return response;
        }
    }
    //@PostMapping
    //public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
    //    return odontologoService.guardarOdontologo(odontologo);
    //}


    // ------------------------USAMOS EL RESPONSE ENTITY CLASE 29----------------------------------------
    @GetMapping("/listar")
    public ResponseEntity <List<Odontologo>> listarOdontologos(){
        List<Odontologo> odontologos = odontologoService.listarOdontologos();
        if(odontologos.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(odontologos);
        }
    }

    //----------------------GET BUSCAR POR ID CON RESPONSENTITY ------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorID (@PathVariable Long id){
        Odontologo odontologBuscado = odontologoService.buscarOdontologoPorId(id);
        if(odontologBuscado != null){
            return ResponseEntity.ok(odontologBuscado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    //@GetMapping("/{id}")
    //public Odontologo buscarOdontologoPorID(@PathVariable int id){
    //    return odontologoService.buscarOdontologoPorId(id);
    //}

    //----------------------DELETE PACIENTE CON RESPONSENTITY ------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        if(odontologoBuscado != null){
            return ResponseEntity.ok("El Odontologo con id: "+ id+" fue eliminado correctamente");
        }else {
            return ResponseEntity.badRequest().body("Eo odontologo con id: "+id+" no se encuentra registrado para ser eliminado.");
        }
    }
    //@DeleteMapping("/{id}")
    //public String eliminarOdontologo(@PathVariable int id){
    //        odontologoService.eliminarOdontologo(id);
    //    return "El Odontologo con ID "+ id +" ha sido eliminado correctamente.";
    //}

    //----------------------ACTUALIZAR PACIENTE CON RESPONSENTITY ------------------------
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoPorId(odontologo.getId());
        if(odontologoBuscado != null){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("El odontologo con id: "+ odontologo.getId()+ " fue actualizado correctamente.");
        }else {
            return ResponseEntity.badRequest().body("El odontologo con id : "+odontologo.getId()+" no se encuentra para se modificado.");

        }
    }

    //@PutMapping
    //public String actualizarOdontologo(@RequestBody Odontologo odontologo){
        //consultamos si el paciente existe
    //   Odontologo odontologoBuscado= odontologoService.buscarOdontologoPorId(odontologo.getId());
    //    if(odontologoBuscado!=null){
    //        odontologoService.actualizarOdontologo(odontologo);
    //        return "Odontologo Actualizado"+ " - "+ odontologo.getNombre();
    //    }else{
    //        return "Odontologo no exite";
    //    }
    //}

}*/
