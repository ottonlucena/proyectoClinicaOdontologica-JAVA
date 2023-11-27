package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaProyectoApplication {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(ClinicaOdontologicaProyectoApplication.class);
		logger.info("Iniciando Logger desde main");
		PropertyConfigurator.configure("log4j.properties");
		SpringApplication.run(ClinicaOdontologicaProyectoApplication.class, args);

	}

}
