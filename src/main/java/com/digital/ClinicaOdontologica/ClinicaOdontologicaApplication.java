package com.digital.Clase23ClinicaOdontologica;

import com.digital.Clase23ClinicaOdontologica.repository.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		//BD.crearTabla();
	}

}
