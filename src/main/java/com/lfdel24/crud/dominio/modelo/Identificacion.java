package com.lfdel24.crud.dominio.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class Identificacion {

    private static final String NUMERO_OBLIGATORIO = "El número es obligatorio";
    private static final String TIPO_IDENTIFICACION_OBLIGATORIO = "El tipo de identificación es obligatorio";

    private TipoIdentificacion tipoIdentificacion;

    private String numero;

    public static Identificacion crear(TipoIdentificacion tipoIdentificacion, String numero) {
        ValidadorArgumento.validarObligatorio(tipoIdentificacion, TIPO_IDENTIFICACION_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(numero, NUMERO_OBLIGATORIO);
        return new Identificacion(tipoIdentificacion, numero);
    }

    public Identificacion() {
    }

    private Identificacion(TipoIdentificacion tipoIdentificacion, String numero) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.numero = numero;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public String getNumero() {
        return numero;
    }

}
