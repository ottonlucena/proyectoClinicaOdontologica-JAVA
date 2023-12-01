package com.clinicaOdontologicaProyecto.clinicaOdontologicaProyecto.exception;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalException {
    Logger logger = Logger.getLogger(GlobalException.class);
    @ExceptionHandler({ResorceNotFoundException.class})
    public ResponseEntity<String> tratamientoResorceNotFoundException(ResorceNotFoundException rnfe){
        //mostramos el error mediante logger
        logger.error(rnfe.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> exceptionBadRequest(BadRequestException badRequestException){

        logger.error(badRequestException.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestException.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(ConstraintViolationException e) {

        return new ResponseEntity<>("Error de validación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleIntegrityConstraintViolation(DataIntegrityViolationException e) {
        return new ResponseEntity<>("Error de violación de clave única: " + e.getMessage(), HttpStatus.CONFLICT);
    }


}
