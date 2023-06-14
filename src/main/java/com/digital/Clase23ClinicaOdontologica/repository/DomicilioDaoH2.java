package com.digital.Clase23ClinicaOdontologica.repository;


import com.digital.Clase23ClinicaOdontologica.model.Domicilio;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class DomicilioDaoH2 implements IDao <Domicilio>{
    //id, calle, numero
    private static final String SQL_INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SQL_BUSCAR_ID_DOMICILIO="SELECT * FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_MODIFICAR="UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=?";
    private static final String SQL_ELIMINAR="DELETE FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_LISTAR="SELECT * FROM DOMICILIOS";
    private static final Logger LOGGER= Logger.getLogger(DomicilioDaoH2.class);

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        // cargar ejecutar y guardar
        LOGGER.debug("Ingresamos al metodo guardar domicilio");
        Connection connection= null;
        try {
            connection= BD.getConnection();
            PreparedStatement pstmt= connection.prepareStatement(SQL_INSERT_DOMICILIO, Statement.RETURN_GENERATED_KEYS);
            //colocamos los datos del insert que craamos y executamos
            pstmt.setString(1, domicilio.getCalle());
            pstmt.setString(2,domicilio.getNumero());
            pstmt.setString(3,domicilio.getLocalidad());
            pstmt.setString(4, domicilio.getProvincia());
            //ejecutar lo que se carga
            pstmt.execute();
            //este es para obtener la clave
            ResultSet claves = pstmt.getGeneratedKeys();
            while (claves.next()) {
                domicilio.setId(claves.getLong(1));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public Domicilio buscarID(Long id) {
        LOGGER.debug("INGRESAMOS AL METODO DE BUSCAR ID POR DOMICILIO : " + id);
        Connection connection=null;
        Domicilio domicilio =null;
        try{
            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_BUSCAR_ID_DOMICILIO);
            preparedStatement.setLong(1,id);

            //se ejecuta la consulta para realizar la busqueda
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long idDomicilio = resultSet.getLong(1);
                String calle= resultSet.getString(2);
                String numero=resultSet.getString(3);
                String localidad=resultSet.getString(4);
                String provincia = resultSet.getString(5);
                domicilio= new Domicilio(idDomicilio,calle,numero,localidad,provincia);

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
        return domicilio;

    }

    @Override
    public void eliminar(Long id) {
        LOGGER.debug("INGRESAMOS AL METODO DE eliminar ID POR DOMICILIO : " + id);
        Connection connection=null;
        try{
            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ELIMINAR);
            preparedStatement.setLong(1,id);

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
    public void modificar(Domicilio domicilio) {
        LOGGER.info("ingresamos al metodo modificar el id: " + domicilio.getId());
        Connection connection=null;

        try{
            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_MODIFICAR);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.setLong(5,domicilio.getId());
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
    public List<Domicilio> listar() {
        LOGGER.info("listamos todos los domicilios");
        Connection connection=null;
        Domicilio domicilio=null;
        List<Domicilio> listarDomicilio = new ArrayList<>();

        try{
            connection = BD.getConnection();
            PreparedStatement psAll = connection.prepareStatement(SQL_LISTAR);

            ResultSet rs=psAll.executeQuery();
            while(rs.next()) {
                Long idDomicilio =rs.getLong(1);
                String calle= rs.getString(2);
                String numero=rs.getString(3);
                String localidad=rs.getString(4);
                String provincia = rs.getString(5);
                domicilio= new Domicilio(idDomicilio,calle,numero,localidad,provincia);

                listarDomicilio.add(domicilio);
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
        return listarDomicilio;
    }

    @Override
    @Deprecated
    public Domicilio buscarXString(String valor) {
        return null;
    }
}
