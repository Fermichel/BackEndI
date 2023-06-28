package com.digital.Clase23ClinicaOdontologica.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Table;



public class Odontologo {
    private Long id;
    private String matricula;
    private String nombre;
    private String apellido;

    public Odontologo(Long id, String matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo() {

    }

    @Override
    public String toString() {
        return "\nOdontologo " +id+
                "\n\tMatricula: " + matricula +
                "\n\tNombre:" + nombre +
                "\n\tApellido:" + apellido +
                "\n----------------------------------------------------------------";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
