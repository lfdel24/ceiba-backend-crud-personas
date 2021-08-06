package com.lfdel24.crud.dominio.exepcion;

public class ExcepcionPersonaRepetida extends RuntimeException {

    public ExcepcionPersonaRepetida(String mensaje) {
        super(mensaje);
    }

}
