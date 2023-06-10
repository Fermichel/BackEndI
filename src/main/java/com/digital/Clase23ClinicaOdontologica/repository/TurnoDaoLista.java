package com.digital.Clase23ClinicaOdontologica.repository;

import com.digital.Clase23ClinicaOdontologica.model.Turno;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnoDaoLista implements IDao<Turno>{
    private Logger LOGGER=Logger.getLogger(TurnoDaoLista.class);
    // agregamos la lista
    private List<Turno> turnos;

    public TurnoDaoLista() {
        turnos = new ArrayList<>();
    }

    @Override
    public Turno guardar(Turno turno) {
        LOGGER.info("Ingresamos al metodo guardar turno id: "+turno.getId());
        PacienteDaoH2 pacienteDao=new PacienteDaoH2();
        OdontologoDaoH2 odontologoDao=new OdontologoDaoH2();

        turno.setPaciente(pacienteDao.buscarID(turno.getPaciente().getId()));
        turno.setOdontologo(odontologoDao.buscarID(turno.getOdontologo().getId()));
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscarID(int id) {
        LOGGER.info("Ingresamos al metodo buscar id: " + id);
        Turno turnoBuscado= null;
        for (Turno turno:turnos) {
            if(turno.getId() == id) {
                turnoBuscado=turno;
            }
        }
        return turnoBuscado;
    }

    @Override
    public void eliminar(int id) {
        LOGGER.info("Se elimina id: "+id);
        Turno turnoBuscado=buscarID(id);
        turnos.remove(turnoBuscado);
    }

    @Override
    public void modificar(Turno turno) {
        LOGGER.info("Se modifico el id: "+turno.getId());
        eliminar(turno.getId());
        guardar(turno);
    }

    @Override
    public List<Turno> listar() {
        LOGGER.info("Se listaron los turnos...");
        return turnos;
    }

    @Override
    public Turno buscarXString(String valor) {
        return null;
    }
}
