package com.digital.Clase23ClinicaOdontologica.repository;


import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class BD {
    //logger
    private static final Logger LOGGER= Logger.getLogger(BD.class);
    //creacion de las tablas
    private static final String SQL_DROP_CREATE_PACIENTE = "DROP TABLE IF EXISTS PACIENTES; " +
            "CREATE TABLE PACIENTES (ID BIGINT AUTO_INCREMENT PRIMARY KEY, APELLIDO VARCHAR(100) NOT NULL, NOMBRE VARCHAR(100) NOT NULL," +
            " EMAIL VARCHAR(100) NOT NULL, DNI VARCHAR(100) NOT NULL, FECHA_DE_INGRESO DATE NOT NULL, DOMICILIO_ID INT NOT NULL)";

    private static final String SQL_DROP_CREATE_DOMICILIO = "DROP TABLE IF EXISTS DOMICILIOS; " +
            "CREATE TABLE DOMICILIOS (ID BIGINT  AUTO_INCREMENT PRIMARY KEY, CALLE VARCHAR(100) NOT NULL, NUMERO VARCHAR(100) NOT NULL," +
            " LOCALIDAD VARCHAR(100) NOT NULL, PROVINCIA VARCHAR(100) NOT NULL)";
    private static final String SQL_DROP_TABLE_ODONTOLOGO = "DROP TABLE IF EXISTS ODONTOLOGOS; " +
            "CREATE TABLE ODONTOLOGOS (ID BIGINT AUTO_INCREMENT PRIMARY KEY, MATRICULA INT NOT NULL, NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100) NOT NULL)";

    private static final String SQL_PRUEBA = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO)" +
            " VALUES ('236','Rocio','Robles'), ('568','Pia','Tuñon');" +
            "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA)" +
            " VALUES ('Jaramillo','85','La Rioja','La Rioja'), ('Jaramillo','90','Cordoba','Cordoba');" +
            "INSERT INTO PACIENTES (NOMBRE, APELLIDO, EMAIL, DNI, FECHA_DE_INGRESO, DOMICILIO_ID)" +
            " VALUES ('Pereyra','Jorge','jorgitopereyra@mail.com','1256789',DATE '2021-08-11',1)," +
            " ('Perez','Martin','prueba2@digitalhouse.com','1289',DATE '2021-08-11',2);";
    //coneccion
    private static final String DRIVER= "org.h2.Driver";
    private static final String URL="jdbc:h2:~/clinicaOdontologica";
    private static final String USER="sa";
    private static final String PASS="sa";


    public static void crearTabla(){
        Connection connection= null;
        try{
            connection= getConnection();
            Statement statement= connection.createStatement();
            statement.execute(SQL_DROP_CREATE_PACIENTE);
            statement.execute(SQL_DROP_CREATE_DOMICILIO);
            statement.execute(SQL_DROP_TABLE_ODONTOLOGO);
            statement.execute(SQL_PRUEBA);
            LOGGER.info("SE CREARON LAS TABLAS CON EXITO");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }



    }
    public static Connection getConnection() throws Exception{
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL,USER,PASS);
    }
}
