package com.digital.Clase23ClinicaOdontologica.service;

import com.digital.Clase23ClinicaOdontologica.repository.IDao;
import com.digital.Clase23ClinicaOdontologica.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
   private IDao<Turno> turnoIdao;

    public TurnoService(IDao<Turno> turnoIdao) {
        this.turnoIdao = turnoIdao;
    }


    public Turno guardarTurno(Turno turno) {
        return turnoIdao.guardar(turno);
    }


    public List<Turno> listarTurnos(){
        return turnoIdao.listar();
    }
    public Turno buscarTurnoPorID(int id){
        return turnoIdao.buscarID(id);
    }

    public void eliminarTurno(int id){
      turnoIdao.eliminar(id);
    }
    public void actualizarTurno(Turno turno){
        turnoIdao.modificar(turno);
    }
}
