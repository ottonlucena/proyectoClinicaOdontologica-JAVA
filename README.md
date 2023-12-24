# Clínica Odontológica Proyecto

Bienvenido al proyecto Clínica Odontológica, una aplicación Java que gestiona pacientes, odontólogos y turnos. Este README proporciona información esencial para comprender y utilizar el proyecto.

## Estructura del Proyecto

- **src/main/java/com/clinicaOdontologicaProyecto/clinicaOdontologicaProyecto/entity:** Contiene las entidades Java relacionadas con la base de datos.
- **src/main/java/com/clinicaOdontologicaProyecto/clinicaOdontologicaProyecto/controller:** Controladores REST para las entidades (Paciente, Odontólogo, Turno).
- **src/main/java/com/clinicaOdontologicaProyecto/clinicaOdontologicaProyecto/service:** Servicios para manejar la lógica de negocio.
- **src/main/java/com/clinicaOdontologicaProyecto/clinicaOdontologicaProyecto/exception:** Clases de excepción personalizadas.
- **src/main/java/com/clinicaOdontologicaProyecto/clinicaOdontologicaProyecto/security:** Configuración de seguridad.
- **src/main/java/com/clinicaOdontologicaProyecto/clinicaOdontologicaProyecto/repository:** Configuración de interface extends de JpaRepository

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

 
