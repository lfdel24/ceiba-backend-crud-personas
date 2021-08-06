package com.lfdel24.crud.dominio.servicio;

import java.util.List;

import com.lfdel24.crud.dominio.exepcion.ExcepcionPersonaRepetida;
import com.lfdel24.crud.dominio.modelo.Persona;
import com.lfdel24.crud.dominio.puerto.repositorio.RepositorioPersona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPersona {

    private static final String LA_PERSONA_YA_EXISTE_EN_EL_SISTEMA = "La persona ya existe en el sistema.";

    @Autowired
    private RepositorioPersona repositorio;

    public List<Persona> findAll() {
        return this.repositorio.findAll();
    }

    public void guardar(Persona persona) {
        grantizarPersonaNoRepetida(persona);
        this.repositorio.save(persona);
    }

    private void grantizarPersonaNoRepetida(Persona persona) {
        if (this.repositorio.findByIdentificacion(persona.getIdentificacion()).isPresent()) {
            throw new ExcepcionPersonaRepetida(LA_PERSONA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

}
