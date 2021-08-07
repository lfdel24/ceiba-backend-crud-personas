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
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/personas")
public class ControladorPersona {

    private final String NO_EXISTE_UNA_PERSONA_CON_EL_ID = "No existe una persona con el id: ";

    @Autowired
    private ServicioPersona servicio;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() throws Exception {
        return ResponseEntity.ok(this.servicio.findAll());
    }

    @PostMapping
    public ResponseEntity<Persona> create(@RequestBody Optional<Map<String, Object>> json) throws Exception {
        Persona persona = this.servicio.guardar(json);
        return ResponseEntity.ok(persona);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") Long id) throws Exception {
        Optional<Persona> optional = this.servicio.get(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) throws Exception {
        this.servicio.delete(id);
        return ResponseEntity.ok().build();
    }

}
