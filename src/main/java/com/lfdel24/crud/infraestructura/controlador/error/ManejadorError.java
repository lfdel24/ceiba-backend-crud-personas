package com.lfdel24.crud.infraestructura.controlador.error;

import java.util.concurrent.ConcurrentHashMap;

import com.lfdel24.crud.dominio.exepcion.ExcepcionPersonaRepetida;
import com.lfdel24.crud.dominio.exepcion.ExcepcionValorObligatorio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManejadorError {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger(ManejadorError.class);
    private static final String OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR = "Ocurrió un error favor contactar al administrador.";

    private static final ConcurrentHashMap<String, Integer> CODIGOS_ESTADO = new ConcurrentHashMap<>();

    public ManejadorError() {
        CODIGOS_ESTADO.put(ExcepcionPersonaRepetida.class.getSimpleName(), HttpStatus.CONFLICT.value());
        CODIGOS_ESTADO.put(ExcepcionValorObligatorio.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllEceptions(Exception exception) {
        ResponseEntity<Error> resultado;
        String excepcionNombre = exception.getClass().getSimpleName();
        String mensaje = exception.getMessage();
        Integer codigo = CODIGOS_ESTADO.get(excepcionNombre);
        if (codigo != null) {
            Error error = new Error(excepcionNombre, mensaje);
            resultado = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
        } else {
            Error error = new Error(excepcionNombre, OCURRIO_UN_ERROR_FAVOR_CONTACTAR_AL_ADMINISTRADOR);
            resultado = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER_ERROR.error(excepcionNombre, exception);
        }
        return resultado;
    }

}
