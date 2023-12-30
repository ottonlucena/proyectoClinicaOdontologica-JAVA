# Clínica Odontológica Proyecto

Bienvenido al proyecto Clínica Odontológica, una aplicación Java que gestiona pacientes, odontólogos y turnos. Este README proporciona información esencial para comprender y utilizar el proyecto.

## Estructura del Proyecto

- **/entity:** Contiene las entidades Java relacionadas con la base de datos.
- **/controller:** Controladores REST para las entidades (Paciente, Odontólogo, Turno).
- **/service:** Servicios para manejar la lógica de negocio.
- **/exception:** Clases de excepción personalizadas.
- **/security:** Configuración de seguridad.
- **/repository:** Configuración de interface extends de JpaRepository

## Dependencias

- **Spring Boot:** Para el desarrollo de aplicaciones Java.
- **H2 Database:** Base de datos en memoria para el entorno de desarrollo.
- **Lombok:** Para simplificar la creación de clases Java.
- **ModelMapper:** Para mapeo de objetos.
- **Log4j:** Herramienta de registro para mensajes de registro.

## Configuración del Entorno

- El proyecto utiliza Maven como herramienta de construcción.
- Se ha configurado una base de datos H2 para el entorno de desarrollo.

## Instrucciones de Compilación y Ejecución

1. Asegúrate de tener Maven instalado.
2. Clona el repositorio o descarga.
3. Ejecuta `mvn clean install` para compilar y construir el proyecto.
4. Ejecuta la aplicación usando `mvn spring-boot:run` o despliega el archivo JAR generado.

## Seguridad

La seguridad está implementada utilizando Spring Security con roles de usuario (USER, ADMIN). Se ha proporcionado un usuario inicial para pruebas dentro de la clase DatosInicialesSecurity puedes encontrarlo y/o cambiarlo.

## Pruebas Unitarias

- Se han incluido pruebas unitarias utilizando JUnit 5 para las entidades Paciente, Odontólogo y Turno.

## Uso de la API

Se han implementado controladores REST para gestionar Pacientes, Odontólogos y Turnos. A continuación, se presentan algunos ejemplos de uso:

### Paciente:
- **Registrar Paciente:**
  /paciente
- **Actualizar Paciente**
  /paciente/actualizar
- **Eliminar Paciente**
  /paciente/eliminar/id
- **Buscar Paciente por ID**
  /paciente/buscarPaciente/id

  ### Odontologo:
- **Registrar Odontologo**
  /odontologo
- **Actualizar Odontologo**
  /odontologo/actualizar
- **Buscar Odontologo por ID**
  /odontologo/buscar/id
- **Eliminar Odontologo**
  /odontologo/eliminar/id

   ### Turno:
- **Registrar Turno**
  /turnos
- **Buscar Turno por ID**
  /turnos/id
- **Eliminar Turno por ID**
  /turnos/id

## Documentación de la API

Esta API está documentada utilizando Swagger para facilitar la comprensión y prueba de los endpoints. A continuación, se proporcionan detalles sobre cómo acceder a la documentación Swagger.  

### Swagger

La documentación detallada de la API se encuentra en Swagger. Puede acceder a la documentación utilizando el siguiente enlace:
  
Swagger Documentación:
[http://localhost:8080/v3/api-docs]  

Swagger URL:
(URL: http://localhost:8080/swagger-ui/index.html)  

### Ejemplo de Uso de Anotaciones Swagger

Aquí hay un ejemplo de cómo hemos utilizado anotaciones Swagger en nuestro código:

```java
 @Operation(summary = "Listar todos los odontologos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontologos encontrados con exito.",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Odontologo.class))
            }),
            @ApiResponse(responseCode = "404", description = "Odontologos no encontrados.")
    })
    @GetMapping
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }


