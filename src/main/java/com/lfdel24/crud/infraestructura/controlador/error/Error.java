package com.lfdel24.crud.infraestructura.controlador.error;

public class Error {

    private String nombreExepcion;
    private String mensaje;

    public Error(String nombreExepcion, String mensaje) {
        this.nombreExepcion = nombreExepcion;
        this.mensaje = mensaje;
    }

    public String getNombreExepcion() {
        return nombreExepcion;
    }

    public String getMensaje() {
        return mensaje;
    }
}
