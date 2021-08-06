package com.lfdel24.crud.dominio.modelo;

import java.time.LocalDate;
import javax.persistence.Embedded;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Persona {

    private static final String IDENTIFICACION_OBLIGATORIA = "La identificación es obligatoria";
    private static final String NOMBRE_COMPLETO_OBLIGATORIO = "El nombre completo es obligatorio";
    private static final String FECHA_NACIMIENTO_OBLIGATORIA = "La fecha de nacimiento es obligatoria";

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Identificacion identificacion;

    private String nombreCompleto;

    private LocalDate fechaNacimiento;

    public static Persona crear(Identificacion identificacion, String nombreCompleto, LocalDate fechaNacimiento) {
        ValidadorArgumento.validarObligatorio(identificacion, IDENTIFICACION_OBLIGATORIA);
        ValidadorArgumento.validarObligatorio(nombreCompleto, NOMBRE_COMPLETO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(fechaNacimiento, FECHA_NACIMIENTO_OBLIGATORIA);
        return new Persona(identificacion, nombreCompleto, fechaNacimiento);
    }

    public Persona() {
    }

    private Persona(Identificacion identificacion, String nombreCompleto, LocalDate fechaNacimiento) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public Identificacion getIdentificacion() {
        return identificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

}
