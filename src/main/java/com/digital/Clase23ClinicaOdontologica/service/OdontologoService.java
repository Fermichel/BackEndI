package com.digital.Clase23ClinicaOdontologica.service;

import com.digital.Clase23ClinicaOdontologica.repository.IDao;
import com.digital.Clase23ClinicaOdontologica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private IDao <Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    public Odontologo buscarOdontologoPorId (int id){
        return odontologoIDao.buscarID(id);
    }
    public List<Odontologo> listarOdontologos(){
        return odontologoIDao.listar();
    }
    public Odontologo buscarOdontologoXpalabra(String palabra){
        return odontologoIDao.buscarXString(palabra);
    }
    public void eliminarOdontologo(int id){
        odontologoIDao.eliminar(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoIDao.modificar(odontologo);
    }

}
