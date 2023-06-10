package com.digital.Clase23ClinicaOdontologica.controller;

import com.digital.Clase23ClinicaOdontologica.model.Odontologo;
import com.digital.Clase23ClinicaOdontologica.model.Paciente;
import com.digital.Clase23ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }
    // ------------------------USAMOS EL RESPONSE ENTITY CLASE 29----------------------------------------
    @GetMapping("/listar")
    public ResponseEntity <List<Odontologo>> listarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologoPorID(@PathVariable int id){
        return odontologoService.buscarOdontologoPorId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarOdontologo(@PathVariable int id){
        odontologoService.eliminarOdontologo(id);
        return "El Odontologo con ID "+ id +" ha sido eliminado correctamente.";
    }

    @PutMapping
    public String actualizarOdontologo(@RequestBody Odontologo odontologo){
        //consultamos si el paciente existe
        Odontologo odontologoBuscado= odontologoService.buscarOdontologoPorId(odontologo.getId());
        if(odontologoBuscado!=null){
            odontologoService.actualizarOdontologo(odontologo);
            return "Odontologo Actualizado"+ " - "+ odontologo.getNombre();
        }else{
            return "Odontologo no exite";
        }
    }




}
