package com.lfdel24.crud.dominio.modelo;

import com.lfdel24.crud.dominio.exepcion.ExcepcionValorObligatorio;

public class ValidadorArgumento {

    private ValidadorArgumento() {
    };

    public static void validarObligatorio(Object valor, String mensaje) {
        if (valor == null || valor.toString().isEmpty()) {
            throw new ExcepcionValorObligatorio(mensaje);
        }
    }

}
