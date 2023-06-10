package com.digital.Clase23ClinicaOdontologica.service;

import com.digital.Clase23ClinicaOdontologica.repository.IDao;
import com.digital.Clase23ClinicaOdontologica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteIDao.guardar(paciente);
    }

    public Paciente buscarPacientePorId (int id){
       return pacienteIDao.buscarID(id);
    }
    public List<Paciente> listarpacientes(){
        return pacienteIDao.listar();
    }
    public Paciente buscarPacienteXEmail(String email){
        return pacienteIDao.buscarXString(email);
    }
    public void eliminarPaciente(int id){
        pacienteIDao.eliminar(id);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteIDao.modificar(paciente);
    }

}
