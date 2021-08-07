package com.lfdel24.crud.dominio.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.lfdel24.crud.dominio.exepcion.ExcepcionPersonaRepetida;
import com.lfdel24.crud.dominio.exepcion.ExcepcionValorObligatorio;
import com.lfdel24.crud.dominio.modelo.Identificacion;
import com.lfdel24.crud.dominio.modelo.Persona;
import com.lfdel24.crud.dominio.modelo.TipoIdentificacion;
import com.lfdel24.crud.dominio.puerto.repositorio.RepositorioPersona;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPersona {

    private static final String LA_PERSONA_YA_EXISTE_EN_EL_SISTEMA = "La persona ya existe en el sistema.";
    private static final String CUERPO_OBLIGATORIO = "Cuerpo obligatorio: {'numero':'valor','nombreCompleto':'valor'}";

    @Autowired
    private RepositorioPersona repositorio;

    public List<Persona> findAll() {
        return this.repositorio.findAll();
    }

    public Optional<Persona> get(Long id) {
        return this.repositorio.findById(id);
    }

    public void delete(Long id) {
        Optional<Persona> optional = get(id);
        if (optional.isPresent()) {
            this.repositorio.deleteById(id);
        }
    }

    public Persona guardar(Optional<Map<String, Object>> json) throws Exception {
        Identificacion identificacion = Identificacion.crear(TipoIdentificacion.CEDULA, obtenerValor(json, "numero"));
        Persona persona = Persona.crear(identificacion, obtenerValor(json, "nombreCompleto"), LocalDate.now());
        grantizarPersonaNoRepetida(persona);
        this.repositorio.save(persona);
        return persona;
    }

    private void grantizarPersonaNoRepetida(Persona persona) {
        if (this.repositorio.findByIdentificacion(persona.getIdentificacion()).isPresent()) {
            throw new ExcepcionPersonaRepetida(LA_PERSONA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

    private String obtenerValor(Optional<Map<String, Object>> json, String llave) throws Exception {
        if (json.isPresent()) {
            return json.map(valor -> valor.get(llave).toString()).orElse("");
        }
        throw new ExcepcionValorObligatorio(CUERPO_OBLIGATORIO);
    }

}
