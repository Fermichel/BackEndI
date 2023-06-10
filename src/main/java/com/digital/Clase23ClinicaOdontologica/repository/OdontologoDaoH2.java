package com.digital.Clase23ClinicaOdontologica.repository;

import com.digital.Clase23ClinicaOdontologica.model.Odontologo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger LOGGER= Logger.getLogger(OdontologoDaoH2.class);
    private static final String SQL_INSERT_ODONTOLOGO="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
    private static final String SQL_LISTAR="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_BUSCAR_ID="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_ELIMINAR="DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_BUSCAR_NOMBRE="SELECT * FROM ODONTOLOGOS WHERE NOMBRE=?";
    private static final String SQL_MODIFICAR="UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID=?";




    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Ingresamos al metodo guardar Odontologo: "+odontologo.getNombre()+" "+odontologo.getApellido());
        Connection connection= null;
        try {
            // MATRICULA, NOMBRE, APELLIDO ¡¡NO USAMOS EL ID!!
            connection=BD.getConnection();
            PreparedStatement ps_registrar =connection.prepareStatement(SQL_INSERT_ODONTOLOGO,Statement.RETURN_GENERATED_KEYS);

            ps_registrar.setInt(1,odontologo.getMatricula());
            ps_registrar.setString(2, odontologo.getNombre());
            ps_registrar.setString(3,odontologo.getApellido());
            ps_registrar.execute();
            //este es para obtener la clave
            ResultSet claves = ps_registrar.getGeneratedKeys();
            while (claves.next()) {
                odontologo.setId(claves.getInt(1));
            }
            LOGGER.info("Se cargo Odontologo con id: "+odontologo.getId());

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarID(int id) {
        LOGGER.debug("Ingresamos al metodo buscar odontologo por id: "+id);

        Connection connection=null;
        Odontologo odontologo = null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_BUSCAR_ID);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                //ID,matricula,nombre, apelido
                odontologo =new Odontologo (rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4));
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
        return odontologo;

    }

    @Override
    public void eliminar(int id) {
        LOGGER.debug("INGRESAMOS AL METODO DE ELIMINAR DE ODONTOLOGO"+ id);
        Connection connection= null;
        try{
            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ELIMINAR);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

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
    public void modificar(Odontologo odontologo) {
        LOGGER.info("ingresamos al metodo modificar el id: " + odontologo.getId());
        Connection connection=null;

        try{
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_MODIFICAR);
            //APELLIDO,NOMBRE,email,DNI(S),FECHA INGRESO, DOMICILIO_ID
            preparedStatement.setInt(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.setInt(4,odontologo.getId());
            preparedStatement.execute();

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
    public List<Odontologo> listar() {
        LOGGER.info("Ingresamos al metodo listar de OdontologoDaoH2");
        Connection connection= null;
        Odontologo odontologo= null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        try {
            connection =BD.getConnection();
            PreparedStatement ps_listar=connection.prepareStatement(SQL_LISTAR);
            //RESULTSET TRAE ELEMENTOS
            ResultSet rs = ps_listar.executeQuery();
            while(rs.next()){
                int idOdontologo = rs.getInt(1);
                int matricula = rs.getInt(2);
                String nombre = rs.getString(3);
                String apellido = rs.getString(4);
                odontologo =new Odontologo(idOdontologo,matricula,nombre,apellido);
                listaOdontologos.add(odontologo);
            }

        }catch (Exception e) {

        }finally {
            try {
                connection.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarXString(String valor) {
        Connection connection=null;
        Odontologo odontologo = null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_BUSCAR_NOMBRE);
            ps.setString(1,valor);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                //ID,matricula,nombre, apelido
                odontologo =new Odontologo (rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4));

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
        return odontologo;
    }
}
