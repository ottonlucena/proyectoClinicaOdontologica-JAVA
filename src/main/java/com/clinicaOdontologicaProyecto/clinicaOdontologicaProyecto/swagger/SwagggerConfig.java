package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//URL: http://localhost:8080/swagger-ui/index.html
@Configuration
@OpenAPIDefinition
public class SwagggerConfig {

    @Bean
    public OpenAPI api(){
        return new OpenAPI().info(new Info().title("API REST con MYSQL - JAVA SPRING-BOOT - PROYECTO CLINICA ODONTOLOGICA").description("API encargada de realizar peticiones (CRUD) a una Base de Datos según los ENDPOINT creados.")
                .contact(new Contact().email("ottonlucena@gmail.com").name("Otton Lucena").url("https://www.linkedin.com/in/ottonlucena/")));
    }

}
