package com.lfdel24.crud.infraestructura.controlador;

import com.lfdel24.crud.dominio.exepcion.ExcepcionValorObligatorio;
import com.lfdel24.crud.dominio.modelo.Identificacion;
import com.lfdel24.crud.dominio.modelo.Persona;
import com.lfdel24.crud.dominio.modelo.TipoIdentificacion;
import com.lfdel24.crud.dominio.servicio.ServicioPersona;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class ControladorPersona {

    private static final String CUERPO_REUERIDO = "Cuerpo requerido: {'numero':'valor','nombreCompleto':'valor'}";

    @Autowired
    private ServicioPersona servicio;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        return ResponseEntity.ok(this.servicio.findAll());
    }

    @PostMapping
    public ResponseEntity<Persona> create(@RequestBody Map<String, Object> json) throws Exception {
        Identificacion identificacion = Identificacion.crear(TipoIdentificacion.CEDULA, obtenerValor(json, "numero"));
        Persona persona = Persona.crear(identificacion, obtenerValor(json, "nombreCompleto"), LocalDate.now());
        this.servicio.guardar(persona);
        return ResponseEntity.ok(persona);
    }

    private String obtenerValor(Map<String, Object> json, String llave) throws Exception {
        if (json.get(llave) == null) {
            throw new ExcepcionValorObligatorio(CUERPO_REUERIDO);
        }
        return json.get(llave).toString();
    }

}
