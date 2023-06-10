package com.digital.Clase23ClinicaOdontologica;

import com.digital.Clase23ClinicaOdontologica.repository.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Clase23ClinicaOdontologicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Clase23ClinicaOdontologicaApplication.class, args);
		BD.crearTabla();
	}

}
