package com.digital.Clase23ClinicaOdontologica.repository;


import com.digital.Clase23ClinicaOdontologica.model.Domicilio;
import com.digital.Clase23ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




@Repository
public class PacienteDaoH2 implements IDao <Paciente>{
    private static final Logger LOGGER= Logger.getLogger(PacienteDaoH2.class);
    private static final String SQL_INSERT_PACIENTE = "INSERT INTO PACIENTES (APELLIDO, NOMBRE, EMAIL, DNI, FECHA_DE_INGRESO, DOMICILIO_ID) VALUES(?,?,?,?,?,?)";
    private static final String SQL_BUSCAR_ID_PACIENTE="SELECT * FROM PACIENTES WHERE ID = ?";
    //ID, APELLIDO,NOMBRE,EMAIL,,DNI(S),FECHA INGRESO,DOMICILIO
    private static final String SQL_MODIFICAR="UPDATE PACIENTES SET APELLIDO=?, NOMBRE=?, EMAIL=?, DNI=?, FECHA_DE_INGRESO=?, DOMICILIO_ID=? WHERE ID=?";
    private static final String SQL_ELIMINAR="DELETE FROM PACIENTES WHERE ID = ?";
    private static final String SQL_BUSCAR_EMAIL_PACIENTE="SELECT * FROM PACIENTES WHERE EMAIL = ?";

    private static final String SQL_LISTAR="SELECT * FROM PACIENTES";

    @Override
    public Paciente guardar(Paciente paciente) {

        LOGGER.debug("Ingresamos al metodo guardar de paciente en PacienteDaoH2");
        Connection connection= null;

        try {
            //id
            DomicilioDaoH2 domicilioDaoH2 =new DomicilioDaoH2();
            Domicilio domicilio =domicilioDaoH2.guardar(paciente.getDomicilio());

            //APELLIDO,NOMBRE,EMAIL,DNI(S),FECHA INGRESO,DOMICILIO ¡¡ NO USAMOS EL ID!!
            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PACIENTE, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,paciente.getApellido());
            preparedStatement.setString(2, paciente.getNombre());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setString(4, paciente.getEmail());
            preparedStatement.setDate(5, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(6, domicilio.getId());
            preparedStatement.execute();

            ResultSet claves = preparedStatement.getGeneratedKeys();
            while (claves.next()) {
                paciente.setId(claves.getInt(1));
            }
        LOGGER.info("Se guardo correctamente el Paciente: "+paciente.getNombre()+" "+paciente.getApellido());

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public Paciente buscarID(int id) {
        LOGGER.debug("Ingresamos al metodo buscar paciente buscar por id: "+id);

        Connection connection=null;
        Paciente paciente = null;
        Domicilio domicilio= null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_BUSCAR_ID_PACIENTE);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            DomicilioDaoH2 domicilioAux=new DomicilioDaoH2();
            while (rs.next()){
                domicilio=domicilioAux.buscarID(rs.getInt(7));
                //ID, APELLIDO,NOMBRE,DNI(S),FECHA INGRESO,DOMICILIO
                paciente =new Paciente (rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(), domicilio);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
               connection.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public void eliminar(int id) {
        LOGGER.debug("INGRESAMOS AL METODO DE eliminar ID POR paciente : " + id);
        Connection connection=null;
        try{
            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ELIMINAR);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            LOGGER.info("Se elimino id: "+id);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

    }


    @Override
    public void modificar(Paciente paciente) {
        LOGGER.info("ingresamos al metodo modificar el id: " + paciente.getId());
        Connection connection=null;

        try{
            connection = BD.getConnection();

            DomicilioDaoH2 daoAux= new DomicilioDaoH2();
            daoAux.modificar(paciente.getDomicilio());

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_MODIFICAR);
            //APELLIDO,NOMBRE,email,DNI(S),FECHA INGRESO, DOMICILIO_ID
            preparedStatement.setString(1, paciente.getApellido());
            preparedStatement.setString(2, paciente.getNombre());
            preparedStatement.setString(3, paciente.getEmail());
            preparedStatement.setString(4, paciente.getDni());
            preparedStatement.setDate(5, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(6,paciente.getDomicilio().getId());
            preparedStatement.setInt(7,paciente.getId());
            preparedStatement.execute();
            LOGGER.info("Se modifico paciente: "+paciente.getNombre()+" "+paciente.getApellido());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Paciente> listar() {
        LOGGER.info("ingresamos al metodo listar Pacientes");
        Connection connection=null;
        List<Paciente> listaPacientes = new ArrayList<>();
        Paciente paciente = null;
        Domicilio domicilio= null;
        try{
            connection = BD.getConnection();
            PreparedStatement psAll = connection.prepareStatement(SQL_LISTAR);

            ResultSet rs=psAll.executeQuery();
            DomicilioDaoH2 domicilioAux=new DomicilioDaoH2();
            while(rs.next()) {
                domicilio=domicilioAux.buscarID(rs.getInt(7));
                //ID, APELLIDO,NOMBRE,DNI(S),FECHA INGRESO,DOMICILIO
                paciente =new Paciente (rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(), domicilio);
                listaPacientes.add(paciente);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

        return listaPacientes;
    }

    @Override
    public Paciente buscarXString(String valor) {
        LOGGER.info("iniciando la busqueda con paciente: " + valor);
        Connection connection=null;
        Paciente paciente = null;
        Domicilio domicilio= null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_BUSCAR_EMAIL_PACIENTE);
            ps.setString(1,valor);
            ResultSet rs= ps.executeQuery();
            DomicilioDaoH2 domicilioAux=new DomicilioDaoH2();
            while (rs.next()){
                domicilio=domicilioAux.buscarID(rs.getInt(7));
                //ID, APELLIDO,NOMBRE,DNI(S),FECHA INGRESO,DOMICILIO
                paciente =new Paciente (rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toLocalDate(), domicilio);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
        return paciente;
    }
}
